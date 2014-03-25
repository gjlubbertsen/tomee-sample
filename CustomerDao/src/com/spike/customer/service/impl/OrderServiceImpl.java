package com.spike.customer.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.spike.customer.dto.OrderDTO;
import com.spike.customer.repo.OrderRepo;
import com.spike.customer.service.OrderService;

@Singleton
@Named
public class OrderServiceImpl implements OrderService {

	@Inject
	private OrderRepo orderRepo;
	
	@Override
	public List<OrderDTO> getOrders() {
	    return orderRepo.getOrders();
	}

}
