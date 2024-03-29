/*
 *
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package com.itran.fgoc.common.swagger;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import static com.itran.fgoc.common.swagger.ApiModelEnumProperties.*;
import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

@ConditionalOnBean(Swagger2DocumentationConfiguration.class)
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class ApiModelEnumPropertyPropertyBuilder implements ModelPropertyBuilderPlugin {
  private final DescriptionResolver descriptions;

  @Autowired
  public ApiModelEnumPropertyPropertyBuilder(DescriptionResolver descriptions) {
    this.descriptions = descriptions;
  }
  
  @Override
  public void apply(ModelPropertyContext context) {
    Optional<ApiModelEnumProperty> annotation = Optional.absent();

    if (context.getAnnotatedElement().isPresent()) {
      annotation = annotation.or(findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
    }
    if (context.getBeanPropertyDefinition().isPresent()) {
      annotation = annotation.or(findPropertyAnnotation(
          context.getBeanPropertyDefinition().get(),
              ApiModelEnumProperty.class));
    }
    if (annotation.isPresent()) {
      context.getBuilder()
          .allowableValues(annotation.transform(toAllowableValues()).orNull())
          .required(annotation.transform(toIsRequired()).or(false))
          .readOnly(annotation.transform(toIsReadOnly()).or(false))
          .allowEmptyValue(annotation.transform(toAllowEmptyValue()).or(false))
          .description(annotation.transform(toDescription(descriptions)).orNull())
          .isHidden(annotation.transform(toHidden()).or(false))
          .type(annotation.transform(toType(context.getResolver())).orNull())
          .position(annotation.transform(toPosition()).or(0))
          .example(annotation.transform(toExample()).orNull());
    }
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }
}
