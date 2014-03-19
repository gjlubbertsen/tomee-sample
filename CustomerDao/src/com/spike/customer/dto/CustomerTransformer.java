package com.spike.customer.dto;

import java.util.ArrayList;
import java.util.List;

import com.spike.customer.domain.Customer;

public enum CustomerTransformer {
	INSTANCE;

	public CustomerDTO toDTO(Customer bo) {
		// customer transform code here
		CustomerDTO dto = new CustomerDTO();
		dto.setId(bo.getId());
		dto.setName(bo.getName());
		dto.setLastname(bo.getLastname());
		dto.setYear(bo.getYear());
		return dto;
	}

	public Customer toBO(CustomerDTO dto) {
		// customer transform code here
		Customer bo = new Customer(dto.getId());
		bo.setName(dto.getName());
		bo.setLastname(dto.getLastname());
		bo.setYear(dto.getYear());
		return bo;
	}

	public List<CustomerDTO> toDTO(List<Customer> BOs) {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer bo : BOs) {
			customerDTOs.add(toDTO(bo));
		}
		return customerDTOs;
	}

}
