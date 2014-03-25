package com.spike.customer.repo;

import java.util.List;

import com.spike.customer.dto.OrderDTO;

public interface OrderRepo {

	List<OrderDTO> getOrders();
}
