package com.sika.code.basic.constant;

import com.sika.code.common.util.EnumUtil;

/**
 * 类型枚举接口
 *
 * @author daiqi
 * @create 2019-05-23 14:58
 */
public interface TypeEnumInf<T> {
    String GET_TYPE_NAME = "getType";
    String GET_CODE_NAME = "getCode";

    /**
     * 获取类型
     */
    T getType();

    /**
     * 获取描述
     */
    String getDesc();

    /**
     * <p>
     * 根据类型查找指定枚举类的枚举对象
     * </p>
     *
     * @param value         : 类型
     * @param typeEnumClass : 类型枚举class
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:26
     */
    static <T extends TypeEnumInf> T find(Object value, Class<T> typeEnumClass) {
        return EnumUtil.find(typeEnumClass, GET_TYPE_NAME, value);
    }

    static <T extends TypeEnumInf> T findByCode(Object value, Class<T> typeEnumClass) {
        return EnumUtil.find(typeEnumClass, GET_CODE_NAME, value);
    }

    /**
     * <p>
     * 判断类型是否存在 存在返回true
     * </p>
     *
     * @param value
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T extends TypeEnumInf> boolean exist(Object value, Class<T> typeEnumClass) {
        return EnumUtil.exist(typeEnumClass, GET_TYPE_NAME, value);

    }

    /**
     * <p>
     * 判断类型是否不存在 不存在返回true
     * </p>
     *
     * @param value
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T extends TypeEnumInf> boolean notExist(Object value, Class<T> typeEnumClass) {
        return !exist(value, typeEnumClass);
    }

    static <T extends TypeEnumInf> String getDesc(Class<T> tClass, Object value) {
        if (value == null || tClass == null) {
            return null;
        }
        T baseEnumType = find(value, tClass);
        if (baseEnumType == null) {
            return null;
        }
        return baseEnumType.getDesc();
    }
}
