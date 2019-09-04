package com.bababadboy.dealermng.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContext工具类
 * @description: 有些类需要使用new来创建对象，
 * 但是类中需要使用spring容器中定义的bean，此时无法通过spring的自动注入来注入我们需要使用的bean
 * @author wangxb (O_o)??)
 * @date 2019-09-03
 */

@Component  // 必须使用Bean注解，否则applicationContext无法注入
public final class SpringContextAssisor implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAssisor.applicationContext = applicationContext;
    }

    /**
     * 获取名字为name的bean
     * @param name Bean的名字
     */
    public static Object getBeanDefinition(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBeanDefinition(String name,Class<T> clazz) {
        return applicationContext.getBean(name,clazz);
    }


}
