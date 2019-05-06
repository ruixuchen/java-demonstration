package com.crx.classloaderdemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MyClassLoader extends ClassLoader{
	// 将加载basepath文件夹下的class
	private String basepath;
	private List names;
	
	public MyClassLoader(String basepath) {
		this.basepath=basepath; 
		this.names=Arrays.asList(new File(basepath).list());	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String) 
	 * 通过重写此方法来实现自定义的类加载，打破了双亲委派模型
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException{
		// 如果跑加载的类名在当前自定义的文件夹下，则由此自定义的类加载器进行加载
		if(names.contains(name+".class")){
			return findClass(name);
		// 否则，交给应用系统类加载器走双亲委派模型进行加载
		}else{
			return MyClassLoader.getSystemClassLoader().loadClass(name);
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData=getClassData(name);
		if(classData==null){
			throw new ClassNotFoundException();
		}else{
			return defineClass(name, classData, 0, classData.length);
		}

	}
	private byte[] getClassData(String name){
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try{
			InputStream in=new FileInputStream(this.basepath+name+".class");
			byte[] buffer=new byte[2048];
			int num=0;
			while((num=in.read(buffer))!=-1){
				out.write(buffer, 0, num);
			}
			in.close();
			byte[] classData=out.toByteArray();
			out.close();
			return classData;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
