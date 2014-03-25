package com.spike.customer.repo.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.spike.customer.domain.Order;
import com.spike.customer.dto.OrderDTO;
import com.spike.customer.dto.OrderTransformer;
import com.spike.customer.repo.OrderRepo;

@Stateful
public class OrderRepoImpl implements OrderRepo {

	@PersistenceContext(unitName = "customer-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	public List<OrderDTO> getOrders() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		cq.select(order);
		TypedQuery<Order> q = entityManager.createQuery(cq);
		
		return OrderTransformer.INSTANCE.toDTO(q.getResultList());
	}

}
