package com.crx.guicedemo;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       
    }
    
    private static void guiceTest(){
    	Injector injector=Guice.createInjector(new MyAppModule());
        Application application=injector.getInstance(Application.class);
        application.work();
    }
}
