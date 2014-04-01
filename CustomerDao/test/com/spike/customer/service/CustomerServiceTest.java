package com.spike.customer.service;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.spike.customer.dto.CustomerDTO;
import com.spike.junit.ejb.EjbContainerTestBase;

@ManagedBean
public class CustomerServiceTest extends EjbContainerTestBase {

	@Inject
	private CustomerService cs;
	
	@Test
	public void createCustomer() throws Exception {
		CustomerDTO createdCustomer = cs.create(new CustomerDTO());
		Assert.assertNotEquals(0, createdCustomer.getId());
	}
}
