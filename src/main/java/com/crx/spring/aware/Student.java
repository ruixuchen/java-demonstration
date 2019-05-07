package com.crx.spring.aware;

public class Student implements SchoolAware{
	private String id;
	private String name;
	private School school;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchool(School school) {
		this.school=school;	
		System.out.println("here we set the school property by Aware");
		System.out.println("the name of the school is:"+school.getName());
		System.out.println("the address of the school is:"+school.getAddress());
	}
	public School getSchool(){
		return this.school;
	}
	@Override
	public String toString(){
		return getName()+" come from "+getSchool().getAddress();
	}
	
}
