package com.itran.fgoc.common.core.valid;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<Enum, Object> {

    private Object[] enumValues;
    private int[] intEnumValues;
    private long[] longEnumValues;
    private String[] stringEnumValues;

    public EnumValidator() {
    }

    @Override
    public void initialize(Enum constraintAnnotation) {
        enumValues = ReflectUtil.getFieldsValue(constraintAnnotation.enumClass());
        intEnumValues = constraintAnnotation.intEnumValues();
        longEnumValues = constraintAnnotation.longEnumValues();
        stringEnumValues = constraintAnnotation.stringEnumValues();
    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if(object == null){
            return true;
        }
        if(!ArrayUtil.isEmpty(intEnumValues)){
            return ArrayUtil.contains(intEnumValues, (int)object);
        }
        if(!ArrayUtil.isEmpty(longEnumValues)){
            return ArrayUtil.contains(longEnumValues, (long)object);
        }
        if(!ArrayUtil.isEmpty(stringEnumValues)){
            return ArrayUtil.contains(stringEnumValues, (long)object);
        }
        return Arrays.asList(enumValues).contains(object);
    }
}