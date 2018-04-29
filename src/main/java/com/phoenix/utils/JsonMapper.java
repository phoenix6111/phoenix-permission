package com.phoenix.utils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

/**
 * User: sheng
 * Date: 2018-04-28 22:35
 * Description: Json处理转换工具类
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //config
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 将Object转换为String
     * @param src
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T src) {
        //如果src为空，直接返回null
        if(src == null) {
            return null;
        }

        try {
            //如果src是String类型的值，则直接返回src，否则调用objectMapper.writeValueAsString()方法
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.error("Parse object to string exception, error : {}",e);
            return null;
        }
    }

    /**
     * 将String类型值转换成Object 类型的对象
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        //如果src为null，或typeReference为null，则直接返回null
        if(src == null || typeReference == null) {
            return null;
        }

        try {
            //如果typeReference为String，则直接返回src，否则
            return typeReference.getType().equals(String.class) ? (T) src : objectMapper.readValue(src,typeReference);
        } catch (Exception e) {
            log.error("Parse String to Object exception, String: {} ,TypeReference<T> : {}, error : {}",src,typeReference,e);
            return null;
        }
    }

}
