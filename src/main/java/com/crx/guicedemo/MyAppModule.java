package com.crx.guicedemo;

import com.google.inject.AbstractModule;

public class MyAppModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(UserService.class).to(UserServiceImpl.class);
		bind(Application.class).to(MyApplication.class);
		
	}

}
