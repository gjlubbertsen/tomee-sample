package com.spike.customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String lastname;
    private int year;
    
    //this one is private
    private int socialSecurityNr;
    
    public Customer() {
    }
    
    public Customer(long id) {
    	this.id = id;
    }

    public Customer(String name, String lastname, int year) {
        this.name = name;
        this.lastname = lastname;
        this.year = year;
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

	public int getSocialSecurityNr() {
		return socialSecurityNr;
	}

	public void setSocialSecurityNr(int socialSecurityNr) {
		this.socialSecurityNr = socialSecurityNr;
	}
   
}
