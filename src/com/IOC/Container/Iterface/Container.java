package com.IOC.Container.Iterface;

import java.util.Set;

import com.IOC.Exception.IocBeanNotNullException;

/**
 * IOC ����
 * @author Administrator
 *
 */
public interface Container {
	/**
	 * ����class ��ȡBean
	 * @param clazz
	 * @return
	 * @throws IocBeanNotNullException 
	 */
	public <T> T getBean(Class<T> clazz) throws IocBeanNotNullException;
	/**
	 * �������ƻ�ȡbean
	 * @param name
	 * @return
	 * @throws IocBeanNotNullException 
	 */
	public <T> T getBeanByName(String name) throws IocBeanNotNullException;
	/**
	 * ע��һ��bean������
	 * @param bean
	 * @return
	 */
	public Object registerBean(Object bean);
	/**
	 * ע��һ��Class��������
	 * @param clazz
	 * @return
	 */
	public Object registerBean(Class<?> clazz);
	/**
	 * ע��һ�������Ƶ�bean������
	 * @param name
	 * @param bean
	 * @return
	 */
	public Object registerBean(String name,Object bean);
	/**
	 * ɾ��һ��bean
	 * @param clazz
	 */
	public void remove(Class<?> clazz);
	/**
	 * ��������ɾ��һ��bean
	 * @param name
	 */
	public void removeByName(String name);
	/**
	 * ��������bean��������
	 * @return
	 */
	public Set<String> getBeanNames();
	/**
	 * ��ʼ��װ��
	 * @throws IocBeanNotNullException 
	 */
	public void initWired() throws IocBeanNotNullException;
	
}
