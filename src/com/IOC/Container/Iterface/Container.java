package com.IOC.Container.Iterface;

import java.util.Set;

import com.IOC.Exception.IocBeanNotNullException;

/**
 * IOC 容器
 * @author Administrator
 *
 */
public interface Container {
	/**
	 * 根据class 获取Bean
	 * @param clazz
	 * @return
	 * @throws IocBeanNotNullException 
	 */
	public <T> T getBean(Class<T> clazz) throws IocBeanNotNullException;
	/**
	 * 根据名称获取bean
	 * @param name
	 * @return
	 * @throws IocBeanNotNullException 
	 */
	public <T> T getBeanByName(String name) throws IocBeanNotNullException;
	/**
	 * 注册一个bean到容器
	 * @param bean
	 * @return
	 */
	public Object registerBean(Object bean);
	/**
	 * 注册一个Class到容器中
	 * @param clazz
	 * @return
	 */
	public Object registerBean(Class<?> clazz);
	/**
	 * 注册一个带名称的bean到容器
	 * @param name
	 * @param bean
	 * @return
	 */
	public Object registerBean(String name,Object bean);
	/**
	 * 删除一个bean
	 * @param clazz
	 */
	public void remove(Class<?> clazz);
	/**
	 * 根据名称删除一个bean
	 * @param name
	 */
	public void removeByName(String name);
	/**
	 * 返回所有bean对象名称
	 * @return
	 */
	public Set<String> getBeanNames();
	/**
	 * 初始化装配
	 * @throws IocBeanNotNullException 
	 */
	public void initWired() throws IocBeanNotNullException;
	
}
