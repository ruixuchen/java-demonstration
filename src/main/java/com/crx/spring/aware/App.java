package com.crx.spring.aware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        byBeanFactory();

    }
    public static void byApplicationContext(){
    	FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext("classpath:/com/crx/spring/aware/applicationContext.xml");
        School school=(School)context.getBean("school");
        System.out.println(school.getAddress());
        BeanPostProcessor beanPostProcessor=new SchoolAwareProcessor(school);
        context.getBeanFactory().addBeanPostProcessor(beanPostProcessor);
        Student student=(Student)context.getBean("student");
        System.out.println(student.toString());
    }
    public static void byBeanFactory(){
	   	 DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
	   	 XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
	   	 reader.loadBeanDefinitions("classpath:/com/crx/spring/aware/applicationContext.xml");
	   	 School school=(School)beanFactory.getBean("school");
	   	 beanFactory.addBeanPostProcessor(new SchoolAwareProcessor(school));
	     Student student=(Student)beanFactory.getBean("student");
	     System.out.println(student.toString());
    }
}