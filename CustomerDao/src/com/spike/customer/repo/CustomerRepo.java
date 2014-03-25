package com.spike.customer.repo;

import java.util.List;

import com.spike.customer.domain.Customer;
import com.spike.customer.dto.CustomerDTO;

public interface CustomerRepo {
	List<CustomerDTO> listAll();
	CustomerDTO list(long id);
	CustomerDTO create(Customer c);
	void delete(long id);
	CustomerDTO update(long id, String name, String lastname, int year);
}
