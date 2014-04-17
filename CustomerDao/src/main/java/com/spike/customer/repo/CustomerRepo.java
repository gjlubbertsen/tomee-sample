package com.spike.customer.repo;

import java.util.List;

import com.spike.customer.domain.Customer;

public interface CustomerRepo {
	List<Customer> listAll();
	Customer list(long id);
	Customer create(Customer c);
	void delete(long id);
	Customer update(long id, String name, String lastname, int year);
}
