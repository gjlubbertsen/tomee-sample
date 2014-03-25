package com.spike.customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String code;

	//@ManyToOne
	//private Customer customer;

	public Order() {
    }
	
	public String getCode() {
		return code;
	}

	/*public Customer getCustomer() {
		return customer;
	}*/

	public long getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/*public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (int) (id ^ (id >>> 32));
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    Order other = (Order) obj;
	    if (id != other.id)
		    return false;
	    return true;
    }
}
