package com.spike.customer.dto;

import java.util.ArrayList;
import java.util.List;

import com.spike.customer.domain.Customer;
import com.spike.customer.domain.Order;

public enum OrderTransformer {

	INSTANCE;

	public OrderDTO toDTO(Order bo) {
		
		//Customer customer = bo.getCustomer();
		//CustomerDTO customerDTO = CustomerTransformer.INSTANCE.toDTO(customer);
		
		OrderDTO dto = new OrderDTO(bo.getId());
		dto.setCode(bo.getCode());
		//dto.setCustomer(customerDTO);
		
		return dto;
	}
	
	public List<OrderDTO> toDTO(List<Order> bos) {
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for (Order bo : bos) {
			orderDTOs.add(toDTO(bo));
		}
		return orderDTOs;
	}
}
