package com.itran.fgoc.common.core.validator;

import cn.hutool.core.util.ReUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author chun
 * @date 2022/5/20 15:45
 */
public class RegexValidator implements ConstraintValidator<Regex, Object> {

    private String regex;

    @Override
    public void initialize(Regex constraintAnnotation) {
        regex = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return object == null  || ReUtil.isMatch(regex, object.toString());
    }
}
