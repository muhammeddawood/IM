package com.internal.im;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BaseTest {

	private static String[] CONTEXT_PATH = new String[]{"/spring/beans.xml"};
	private static ApplicationContext applicationContext = null;

	
	public BaseTest() {
		if(applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(CONTEXT_PATH);
		}
	}
	
}
