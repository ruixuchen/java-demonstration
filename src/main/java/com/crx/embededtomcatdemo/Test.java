package com.crx.embededtomcatdemo;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

public class Test {

	public static void main(String[] args) throws LifecycleException, ServletException {
//		Tomcat tomcat=new Tomcat();
//		tomcat.setPort(8080);
//		tomcat.setBaseDir(".");
//		
//		StandardServer server=(StandardServer)tomcat.getServer();
//		AprLifecycleListener listener = new AprLifecycleListener();
//		server.addLifecycleListener(listener);
//		tomcat.addWebapp("/demo",new File("WebContent").getAbsolutePath());
//		tomcat.start();
//		String path=System.getProperty("sun.boot.class.path");
//		//java.ext.dirs
//		//java.class.path
//		String path1=System.getProperty("java.ext.dirs");
//		System.out.println(path1);
		//System.getProperties().list(System.out);
	}

}
