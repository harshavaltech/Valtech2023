package com.valtech.assignment.Jdbc.components;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
@Id
private int compId;
private String name;
private String address;

public Company() {
	super();
	// TODO Auto-generated constructor stub
}

public Company(int compId, String name, String address) {
	super();
	this.compId = compId;
	this.name = name;
	this.address = address;
}

public int getCompId() {
	return compId;
}
public void setCompId(int compId) {
	this.compId = compId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}



	
	
	
}
