package com.spike.customer.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="order")
public class OrderDTO {
	private long id;
	private String code;
	private CustomerDTO customer;
	
	public OrderDTO() {
    }
	
	public OrderDTO(long id) {
		this.id = id;
    }
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	
}
