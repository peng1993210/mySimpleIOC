package com.IOC.Exception;
/**
 * ioc容器的实例 获取为空异常
 * @author Administrator
 *
 */
public class IocBeanNotNullException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IocBeanNotNullException(){
		super();
	}
	public IocBeanNotNullException(String message){
		super(message);
	}
}
