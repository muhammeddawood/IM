package com.im;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ServiceLocator implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(ServiceLocator.class);

	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext) {
		ServiceLocator.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * This method assumes that the bean created has the same name as the class.
	 * For e.g. if class name is com.im.util.ImBean then the bean name is "imBean"
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> clazz) {
		//can't fool me with null!
		if (clazz == null) {
			return null;
		}
		
		//we need to extract the name of the class. this is what is donr in next few lines...
		int servicePackageLength = clazz.getPackage().getName().length() + 1;
		//take the string after the package name
		StringBuffer serviceName = new StringBuffer(clazz.getName().substring(servicePackageLength));
		//then make the first character lowercase
		serviceName.setCharAt(0, Character.toLowerCase(serviceName.charAt(0)));

		return (T) getService(serviceName.toString());
	}
	
	public static Object getService(String beanName) {
		if (applicationContext == null) {
			logger.warn("The Spring Application Context has not yet " +
							"initialized, so we can't obtain the bean named "
							+ beanName);
			return null;
		}

		Object o = applicationContext.getBean(beanName);
		return o;
	}
	
	public static <T> T getService(String beanName, Class<T> requiredType){
		if (applicationContext == null) {
			logger.warn("The Spring Application Context has not yet " +
							"initialized, so we can't obtain the bean named "
							+ beanName);
			return null;
		}
		
		return applicationContext.getBean(beanName, requiredType);

	}
	
	/**
	 * Returns the service by the class passed.
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public static <T> T getServiceByType(Class<T> requiredType) {
		if (applicationContext == null) {
			logger.warn("The Spring Application Context has not yet " +
							"initialized, so we can't obtain the bean of type "
							+ requiredType.getName());
			return null;
		}
		
		return applicationContext.getBean(requiredType);
	}
}
