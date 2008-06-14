package com.laborguru.model;

public class Office extends SpmObject {

	int id;
	
	String name;
	
	char type;
	
	Office parentOffice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Office getParentOffice() {
		return parentOffice;
	}

	public void setParentOffice(Office parentOffice) {
		this.parentOffice = parentOffice;
	}
	
}
