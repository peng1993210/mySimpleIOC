package com.IOC.Exception;
/**
 * ioc������ʵ�� ��ȡΪ���쳣
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
