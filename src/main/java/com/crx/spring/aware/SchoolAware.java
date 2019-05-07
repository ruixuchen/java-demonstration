package com.crx.spring.aware;

import org.springframework.beans.factory.Aware;

public interface SchoolAware extends Aware{
	
	public void setSchool(School school);
}
