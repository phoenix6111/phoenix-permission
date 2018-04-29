package com.phoenix.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * User: sheng
 * Date: 2018-04-28 23:13
 * Description: 全局的ApplicationContext 获取Spring上下文工具类
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 根据class类型，返回相应的对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T popBean(Class<T> clazz) {
        if(applicationContext == null) {
            return null;
        }

        return applicationContext.getBean(clazz);
    }

    /**
     * 根据beanName和类型获取Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T popBean(String name,Class<T> clazz) {
        if(applicationContext == null) {
            return null;
        }

        return applicationContext.getBean(name,clazz);
    }
}
