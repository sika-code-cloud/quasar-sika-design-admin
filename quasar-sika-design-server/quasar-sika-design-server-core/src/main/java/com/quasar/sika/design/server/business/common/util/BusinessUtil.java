package com.quasar.sika.design.server.business.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class BusinessUtil {
    private static final String ID_KEY = "id";

    public static <DTO extends BaseStandardDTO> Set<Long> mapToId(List<DTO> dtos, Class<DTO> dtoClass) {
        return mapToField(dtos, ID_KEY, dtoClass, Long.class);
    }

    public static <ObjectType, FieldValueType> Set<FieldValueType> mapToField(List<ObjectType> dtos, String fieldName, Class<ObjectType> objectTypeClass, Class<FieldValueType> fieldValueTypeClass) {
        Set<FieldValueType> values = Sets.newLinkedHashSet();
        if (CollUtil.isEmpty(dtos)) {
            return values;
        }
        if (StrUtil.isBlank(fieldName) || BaseUtil.isNull(objectTypeClass)) {
            return values;
        }
        Field field = ReflectUtil.getField(objectTypeClass, fieldName);
        for (Object dto : dtos) {
            Object fieldValue = ReflectUtil.getFieldValue(dto, field);
            if (BaseUtil.isNull(fieldValue)) {
                continue;
            }
            if (fieldValue.getClass().isAssignableFrom(fieldValueTypeClass)) {
                values.add((FieldValueType) fieldValue);
            }
        }
        return values;
    }
}
