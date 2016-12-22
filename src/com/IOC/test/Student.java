package com.IOC.test;

import com.IOC.Annotation.AutoWired;
import com.IOC.Container.Iterface.Container;
import com.IOC.Container.IterfaceImp.SimpleContainer;
import com.IOC.Exception.IocBeanNotNullException;

public class Student {
	private Integer age;
	private String name;
	@AutoWired
	private Cat cat;
	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}











	public void show(){
		cat.say();
		dog.wang();
	}

	@AutoWired
	private Dog dog;
	
	
	
	public static void main(String[] args) {
		Container container=new SimpleContainer();
		container.registerBean(Student.class);
		try {
			container.initWired();
			Student stu=container.getBean(Student.class);
			stu.show();

		} catch (IocBeanNotNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
