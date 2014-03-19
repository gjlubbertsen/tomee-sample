package com.spike.customer.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.spike.customer.dao.CustomerDao;
import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.dto.CustomerTransformer;
import com.spike.customer.service.CustomerService;

@Singleton
@Named
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerDao customerDao;
	
	@Override
    public CustomerDTO create(CustomerDTO dto) {
		return customerDao.create(CustomerTransformer.INSTANCE.toBO(dto));
    }

	@Override
    public CustomerDTO list(long customerId) {
		return customerDao.list(customerId);
    }

	@Override
    public List<CustomerDTO> getCustomers() {
		return customerDao.listAll();
    }

	@Override
    public void delete(long id) {
		customerDao.delete(id);
    }

	@Override
    public CustomerDTO update(long id, String name, String lastname, int year) {
		 return customerDao.update(id, name, lastname, year);
    }

}
