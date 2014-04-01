package com.spike.customer.service;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.spike.customer.dto.CustomerDTO;

@ManagedBean
public class CustomerServiceTest extends EjbContainerTestBase {

	@Inject
	private CustomerService cs;
	
	@Test
	public void createCustomer() throws Exception {
		CustomerDTO d = new CustomerDTO();
		d.setName("GJ");
		d.setLastname("Lubb");
		d.setYear(1984);
		CustomerDTO createdCustomer = cs.create(new CustomerDTO());
		Assert.assertNotEquals(0, createdCustomer.getId());
	}
}
