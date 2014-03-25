package com.spike.customer.service;

import java.util.List;

import com.spike.customer.dto.OrderDTO;

public interface OrderService {

	List<OrderDTO> getOrders();
}
