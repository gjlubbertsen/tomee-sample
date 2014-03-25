package com.spike.customer.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.spike.customer.dto.OrderDTO;
import com.spike.customer.service.OrderService;

@Path("/spike")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class OrderController {

	@Inject
	private OrderService orderService;
	
	@Path("/orders")
    @GET
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }
}
