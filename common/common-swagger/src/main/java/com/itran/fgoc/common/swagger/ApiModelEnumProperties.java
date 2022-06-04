package com.itran.fgoc.common.swagger;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.itran.fgoc.common.core.api.Enums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableRangeValues;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.spring.web.DescriptionResolver;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.StringUtils.hasText;

public final class ApiModelEnumProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiModelEnumProperties.class);
    private static final Pattern RANGE_PATTERN = Pattern.compile("range([\\[(])(.*),(.*)([])])$");
    private static final String MSG_ENUMS_KEY = "MSG";

    private ApiModelEnumProperties() {
        throw new UnsupportedOperationException();
    }

    static Function<ApiModelEnumProperty, AllowableValues> toAllowableValues() {
        return new Function<ApiModelEnumProperty, AllowableValues>() {
            @Override
            public AllowableValues apply(ApiModelEnumProperty annotation) {
                return allowableValueFromString(annotation.allowableValues());
            }
        };
    }

    public static AllowableValues allowableValueFromString(String allowableValueString) {
        AllowableValues allowableValues = new AllowableListValues(Lists.<String>newArrayList(), "LIST");
        String trimmed = allowableValueString.trim().replaceAll(" ", "");
        Matcher matcher = RANGE_PATTERN.matcher(trimmed);
        if (matcher.matches()) {
            if (matcher.groupCount() != 4) {
                LOGGER.warn("Unable to parse range specified {} correctly", trimmed);
            } else {
                allowableValues = new AllowableRangeValues(
                        matcher.group(2).contains("infinity") ? null : matcher.group(2),
                        matcher.group(1).equals("("),
                        matcher.group(3).contains("infinity") ? null : matcher.group(3),
                        matcher.group(4).equals(")"));
            }
        } else if (trimmed.contains(",")) {
            Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(trimmed);
            allowableValues = new AllowableListValues(newArrayList(split), "LIST");
        } else if (hasText(trimmed)) {
            List<String> singleVal = Collections.singletonList(trimmed);
            allowableValues = new AllowableListValues(singleVal, "LIST");
        }
        return allowableValues;
    }

    static Function<ApiModelEnumProperty, Boolean> toIsRequired() {
        return new Function<ApiModelEnumProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelEnumProperty annotation) {
                return annotation.required();
            }
        };
    }

    static Function<ApiModelEnumProperty, Integer> toPosition() {
        return new Function<ApiModelEnumProperty, Integer>() {
            @Override
            public Integer apply(ApiModelEnumProperty annotation) {
                return annotation.position();
            }
        };
    }

    static Function<ApiModelEnumProperty, Boolean> toIsReadOnly() {
        return new Function<ApiModelEnumProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelEnumProperty annotation) {
                return annotation.readOnly();
            }
        };
    }

    static Function<ApiModelEnumProperty, Boolean> toAllowEmptyValue() {
        return new Function<ApiModelEnumProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelEnumProperty annotation) {
                return annotation.allowEmptyValue();
            }
        };
    }

    static Function<ApiModelEnumProperty, String> toDescription(
            final DescriptionResolver descriptions) {

        return new Function<ApiModelEnumProperty, String>() {
            @Override
            public String apply(ApiModelEnumProperty annotation) {
                String description = "";
                if (!Strings.isNullOrEmpty(annotation.value())) {
                    description = annotation.value();
                } else if (!Strings.isNullOrEmpty(annotation.notes())) {
                    description = annotation.notes();
                }

                // 加上枚举描述
                Object[] fieldsValue = ReflectUtil.getFieldsValue(annotation.enumClass());
                Enums msg = (Enums) ReflectUtil.getFieldValue(annotation.enumClass(), MSG_ENUMS_KEY);
                for (int i = 0; i < fieldsValue.length; i++) {
                    if(fieldsValue[i] instanceof Enums){
                        continue;
                    }
                    description += "  " + fieldsValue[i] + ":" + msg.name(fieldsValue[i]) + ";";
                }
                return descriptions.resolve(description);
            }
        };
    }

    static Function<ApiModelEnumProperty, ResolvedType> toType(final TypeResolver resolver) {
        return new Function<ApiModelEnumProperty, ResolvedType>() {
            @Override
            public ResolvedType apply(ApiModelEnumProperty annotation) {
                try {
                    return resolver.resolve(Class.forName(annotation.dataType()));
                } catch (ClassNotFoundException e) {
                    return resolver.resolve(Object.class);
                }
            }
        };
    }

    public static Optional<ApiModelEnumProperty> findApiModePropertyAnnotation(AnnotatedElement annotated) {
        Optional<ApiModelEnumProperty> annotation = Optional.absent();

        if (annotated instanceof Method) {
            // If the annotated element is a method we can use this information to check superclasses as well
            annotation = Optional.fromNullable(AnnotationUtils.findAnnotation(((Method) annotated), ApiModelEnumProperty.class));
        }

        return annotation.or(Optional.fromNullable(AnnotationUtils.getAnnotation(annotated, ApiModelEnumProperty.class)));
    }

    static Function<ApiModelEnumProperty, Boolean> toHidden() {
        return new Function<ApiModelEnumProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelEnumProperty annotation) {
                return annotation.hidden();
            }
        };
    }

    static Function<ApiModelEnumProperty, String> toExample() {
        return new Function<ApiModelEnumProperty, String>() {
            @Override
            public String apply(ApiModelEnumProperty annotation) {
                String example = "";
                if (!Strings.isNullOrEmpty(annotation.example())) {
                    example = annotation.example();
                }
                return example;
            }
        };
    }
}