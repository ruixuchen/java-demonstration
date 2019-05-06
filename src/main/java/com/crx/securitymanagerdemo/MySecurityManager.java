package com.crx.securitymanagerdemo;

public class MySecurityManager extends SecurityManager{
	
	public void checkExit(int status){
		System.out.println("运行了安全管理器");
		super.checkRead("/");
		throw new SecurityException("you can not exit.");
	}

}
