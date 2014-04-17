package com.spike.customer.service;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.spike.customer.dto.CustomerDTO;
import com.spike.junit.ejb.EjbContainerTestBase;

@ManagedBean
public class CustomerServiceTest extends EjbContainerTestBase {

	//@Inject
	private CustomerService cs;
	
	@Test
	public void exception_expected_when_customer_not_found()  {
		CustomerDTO c = cs.create(new CustomerDTO());
		Assert.assertEquals(0, cs.listSSN(c.getId()));
	}
	
	/*@Test
	public void getCustomers_returns_two_customers() throws Exception {
		cs.create(new CustomerDTO());
		cs.create(new CustomerDTO());
		Assert.assertEquals(2, cs.getCustomers().size());
	}
	
	*/

}
