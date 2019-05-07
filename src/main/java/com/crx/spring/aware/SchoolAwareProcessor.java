package com.crx.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SchoolAwareProcessor implements BeanPostProcessor{

	private School school;
	
	public SchoolAwareProcessor(School school){
		this.school=school;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof SchoolAware){
			((SchoolAware) bean).setSchool(school);
		}
		return bean;
	}

}
