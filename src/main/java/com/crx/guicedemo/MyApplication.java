package com.crx.guicedemo;

import com.google.inject.Inject;

public class MyApplication implements Application{
	private UserService userService;
	
	@Inject
	public MyApplication(UserService userService) {
		this.userService=userService;
	}
	
	@Override
	public void work() {
		System.out.println("主程序开始执行！");
		userService.process();
	}

}
