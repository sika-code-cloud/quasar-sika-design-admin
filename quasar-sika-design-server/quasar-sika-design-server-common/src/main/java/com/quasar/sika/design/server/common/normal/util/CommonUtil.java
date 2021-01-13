package com.quasar.sika.design.server.common.normal.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * @author daiqi
 * @create 2021-01-08 0:23
 */
public class CommonUtil {
    private static final String ID_KEY = "id";

    /**
     * <p>
     * 将DTO转化为id列表
     * </p>
     *
     * @param dtos
     * @return java.util.Set<java.lang.Long>
     * @author daiqi
     * @date 2021/1/8 0:16
     */
    public static <DTO extends BaseStandardDTO> Set<Long> mapToId(List<DTO> dtos) {
        return mapToField(dtos, ID_KEY);
    }

    /**
     * <p>
     * 将指定对象列表转化成指定属性名称对应的列表
     * </p>
     *
     * @param objs
     * @param fieldName
     * @return java.util.Set<FieldValueType>
     * @author daiqi
     * @date 2021/1/8 0:24
     */
    public static <T> Set<T> mapToField(List<?> objs, String fieldName) {
        Set<T> values = Sets.newLinkedHashSet();
        if (CollUtil.isEmpty(objs)) {
            return values;
        }
        if (StrUtil.isBlank(fieldName)) {
            return values;
        }
        Field field = ReflectUtil.getField(objs.get(0).getClass(), fieldName);
        for (Object dto : objs) {
            T fieldValue = (T) ReflectUtil.getFieldValue(dto, field);
            if (BaseUtil.isNull(fieldValue)) {
                continue;
            }
            values.add(fieldValue);
        }
        return values;
    }

}
