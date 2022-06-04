package com.itran.fgoc.common.core.valid;

import com.itran.fgoc.common.core.var.NullVar;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验字段是否是枚举中，null值不校验
 * 校验顺序：intEnumValues -> longEnumValues -> stringEnumValues -> enumClass
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Enum.List.class)
@Documented
@Constraint(
    validatedBy = EnumValidator.class
)
public @interface Enum {
    String message() default "枚举字段不正确";

    Class<?> enumClass() default NullVar.class;

    int[] intEnumValues() default {};

    long[] longEnumValues() default {};

    String[] stringEnumValues() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Enum[] value();
    }
}
