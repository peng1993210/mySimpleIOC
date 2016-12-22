package com.IOC.test;

public class Cat {
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	private Integer age;
	private String sex;
	
	
	public void say(){
		System.out.println("i am is a cat");
	}
}
