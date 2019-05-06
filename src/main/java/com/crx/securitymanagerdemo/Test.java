package com.crx.securitymanagerdemo;

public class Test {

	public static void main(String[] args) {
		MySecurityManager securityManager=new MySecurityManager();
		System.out.println(System.getSecurityManager());
		System.setSecurityManager(securityManager);
		System.exit(0);

	}

}
