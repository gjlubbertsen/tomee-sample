package com.spike.customer.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.dto.CustomerTransformer;
import com.spike.customer.repo.CustomerRepo;
import com.spike.customer.service.CustomerService;

@Default
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerRepo repo;
	
	@Override
    public CustomerDTO create(CustomerDTO dto) {
		return repo.create(CustomerTransformer.INSTANCE.toBO(dto));
    }

	@Override
    public CustomerDTO list(long customerId) {
		return repo.list(customerId);
    }

	@Override
    public List<CustomerDTO> getCustomers() {
		return repo.listAll();
    }

	@Override
    public void delete(long id) {
		repo.delete(id);
    }

	@Override
    public CustomerDTO update(long id, String name, String lastname, int year) {
		 return repo.update(id, name, lastname, year);
    }

	@Override
	public int listSSN(long customerId) {
		return 0;
	}

	@Override
	public int setSSN(long id, long ssn) {
		return 0;
	}

}
