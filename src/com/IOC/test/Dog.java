package com.IOC.test;

public class Dog {
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
	private Integer age;
	private String name;
	public void wang(){
		System.out.println("wangwangwang");
	}
}
