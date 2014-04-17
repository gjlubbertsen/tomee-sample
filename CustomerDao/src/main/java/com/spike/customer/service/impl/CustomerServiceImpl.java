package com.spike.customer.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import com.spike.customer.domain.Customer;
import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.dto.CustomerTransformer;
import com.spike.customer.repo.CustomerRepo;
import com.spike.customer.service.CustomerService;
import com.spike.customer.service.SSNNotSetException;

@Default
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerRepo repo;
	
	@Inject 
	CustomerTransformer trans;
	
	@Override
    public CustomerDTO create(CustomerDTO dto) {
		return trans.toDTO(repo.create(trans.toBO(dto)));
    }

	@Override
    public CustomerDTO list(long customerId) {
		return trans.toDTO(repo.list(customerId));
    }

	@Override
    public List<CustomerDTO> getCustomers() {
		return trans.toDTO(repo.listAll());
    }

	@Override
    public void delete(long id) {
		repo.delete(id);
    }

	@Override
    public CustomerDTO update(long id, String name, String lastname, int year) {
		 return trans.toDTO(repo.update(id, name, lastname, year));
    }

	@Override
	public int listSSN(long customerId) {
		Customer c = repo.list(customerId);
		if (c == null) {
			throw new EntityNotFoundException();
		}
		int ssn = c.getSocialSecurityNr();
		if (ssn == 0) {
			throw new SSNNotSetException();
		}
		return ssn;
	}

	@Override
	public void setSSN(long id, int ssn) {
		Customer c = repo.list(id);
		if (c == null) {
			throw new EntityNotFoundException();
		}
		if (ssn == 0) {
			throw new SSNNotSetException();
		}
		c.setSocialSecurityNr(ssn);
	}

}
