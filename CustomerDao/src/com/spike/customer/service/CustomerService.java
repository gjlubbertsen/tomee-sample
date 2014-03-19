package com.spike.customer.service;

import java.util.List;

import com.spike.customer.dto.CustomerDTO;

public interface CustomerService {

	CustomerDTO create(CustomerDTO dto);
	CustomerDTO list(long customerId);
	List<CustomerDTO> getCustomers();
	void delete(long id);
	CustomerDTO update(long id, String name, String lastname, int year);
}
