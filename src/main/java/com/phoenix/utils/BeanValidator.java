package com.phoenix.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.phoenix.exception.ParamException;

import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * User: sheng
 * Date: 2018-04-28 19:28
 * Description: 数据校验工具类
 */
public class BeanValidator {

    //创建数据校验工厂
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T>Map<String,String> validate(T t,Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResults = validator.validate(t,groups);
        //如果validateResults为空，则返回一个空的Map，否则返回校验信息
        if(validateResults.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap<String,String> errors = Maps.newLinkedHashMap();
            for (ConstraintViolation<T> violation : validateResults) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    /**
     * 检测Collection类型
     * @param collection
     * @return
     */
    public static Map<String,String> validateList(Collection<?> collection) {
        //检测collection不为空，如果为空，则直接抛出异常
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map<String,String> errors = null;
        //对于collection中的进行迭代，如果有一项检测出错误即返回，不进行其它检测
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        } while (errors.isEmpty());

        return errors;
    }

    /**
     * 通用的验证方法，封装了验证Collection类型和验证普通类的对象
     * @param first
     * @param objects
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            return validateList(Lists.asList(first, objects));
        } else {
            return validate(first, new Class[0]);
        }
    }

    /**
     * 直接检验对象，如果存在错误，则直接抛出ParamException
     * @param param
     * @throws ParamException
     */
    public static void check(Object param) throws ParamException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }
    }

}
