package com.sika.code.basic.constant;

/**
 * @author daiqi
 * @create 2020-03-19 23:06
 */
public interface BeanMappingEnum<T> extends TypeEnumInf<Integer> {
    String getBeanName();
    Class<T> getBeanClass();


}
