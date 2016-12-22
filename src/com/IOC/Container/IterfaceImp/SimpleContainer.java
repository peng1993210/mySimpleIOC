package com.IOC.Container.IterfaceImp;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.IOC.Annotation.AutoWired;
import com.IOC.Container.Iterface.Container;
import com.IOC.Exception.IocBeanNotNullException;
import com.IOC.Utils.ReflectUtil;
/**
 * 容器实现类
 * @author Administrator
 *
 */
public class SimpleContainer implements Container{
	
	private Map<String, Object> beans;
	private Map<String,String> beanKeys;
	public  SimpleContainer() {
		this.beans=new ConcurrentHashMap<String,Object>();
		this.beanKeys=new ConcurrentHashMap<String,String>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(Class<T> clazz) throws IocBeanNotNullException {
		String name=clazz.getName();
		Object object=beans.get(name);
		if(null==object)
			throw new IocBeanNotNullException("没有可用bean");
		return (T) object;
	
	}

	@Override
	public <T> T getBeanByName(String name) throws IocBeanNotNullException {
		String clasName=beanKeys.get(name);
		Object object=beans.get(clasName);
		if(null==object)
			throw new IocBeanNotNullException("没有可用bean");
		return (T) object;
	}

	@Override
	public Object registerBean(Object bean) {
		String name=bean.getClass().getName();
		beanKeys.put(name, name);
		beans.put(name, bean);
		return bean;
	}

	@Override
	public Object registerBean(Class<?> clazz) {
		String name=clazz.getName();
		beanKeys.put(name, name);
		Object bean=ReflectUtil.newInstance(clazz);
		beans.put(name, bean);
		return bean;
	}

	@Override
	public Object registerBean(String name, Object bean) {
		String className=bean.getClass().getName();
		beanKeys.put(name, className);
		beans.put(className, bean);
		return bean;
	}

	@Override
	public void remove(Class<?> clazz) {
		String className=clazz.getName();
		if(null!=className&&!className.equals("")){
			beanKeys.remove(className);
			beans.remove(className);
		}
	}

	@Override
	public void removeByName(String name) {
		String className=beanKeys.get(name);
		if(null!=className&&!className.equals("")){
			beanKeys.remove(name);
			beans.remove(className);
		}
	}

	@Override
	public Set<String> getBeanNames() {
		
		return beanKeys.keySet();
	}

	@Override
	public void initWired() throws IocBeanNotNullException {
		Iterator<Entry<String, Object>> it=beans.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Object> entry=(Entry<String, Object>)it.next();
			Object object=entry.getValue();
			injection(object);
		}
	}

	/**
	 * 注入对象
	 * @param object
	 * @throws IocBeanNotNullException 
	 */
	private void injection(Object object) throws IocBeanNotNullException {
		Field[] fields=object.getClass().getDeclaredFields();
		for(Field field:fields){
			AutoWired auto=field.getAnnotation(AutoWired.class);
			if(auto!=null){
				//注解不为空
				Object autoWiredField=null;
				String name=auto.name();
				if(!name.equals("")){
					//给了名字
					String className=beanKeys.get(name);
					if(null!=className&&!className.equals("")){
						autoWiredField=beans.get(className);
					}
					if(null==autoWiredField){
						throw new RuntimeException("Unable to load"+name);
					}
				}else{
					if(auto.value()==Class.class){
						autoWiredField=this.recursiveAssembly(field.getType());
					}else{
						autoWiredField=this.getBean(auto.value());
						if(null==autoWiredField){
							autoWiredField=recursiveAssembly(auto.value());
						}
					}
				}
				if(null==autoWiredField){
					throw new RuntimeException("Unable to load "+field.getType().getCanonicalName());
				}
				boolean accessible=field.isAccessible();
				field.setAccessible(true);
				try {
					field.set(object, autoWiredField);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				field.setAccessible(accessible);
			}
		}
	}

	private Object recursiveAssembly(Class<?> value) {
		if(null!=value){
			return this.registerBean(value);
		}
		return null;
	}

}
