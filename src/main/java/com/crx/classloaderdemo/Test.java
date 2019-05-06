package com.crx.classloaderdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		test();
	}
	
	private static void test()throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		ClassLoader myClassLoader=new MyClassLoader("F:\\");
		//sun.misc.Launcher$AppClassLoader
		System.out.println(myClassLoader.getClass().getClassLoader());
		//sun.misc.Launcher$AppClassLoader
		System.out.println("默认的系统类加载器是："+myClassLoader.getSystemClassLoader());
		//sun.misc.Launcher$AppClassLoader
		System.out.println(myClassLoader.getParent());
		//sun.misc.Launcher$ExtClassLoader
		System.out.println(myClassLoader.getParent().getParent());
		//null,其中类加载器BootStrap不是jvm管理的，是jni C++实现的
		System.out.println(myClassLoader.getParent().getParent().getParent());
		Class clazz=myClassLoader.loadClass("Student");
		Constructor constructor=clazz.getConstructor(String.class,String.class);
		Object stu=constructor.newInstance("chenruixu","2016");
		Method method=stu.getClass().getMethod("display");
		method.invoke(stu);
	}
}
