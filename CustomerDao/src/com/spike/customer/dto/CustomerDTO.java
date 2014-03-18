package com.spike.customer.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customer")
public class CustomerDTO {

	private long id;
	private String name;
	private String lastname;
	private int year;

	public CustomerDTO() {
	}

	public CustomerDTO(Long id) {
    	this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
