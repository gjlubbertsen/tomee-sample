package com.spike.customer.repo.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.spike.customer.domain.Customer;
import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.dto.CustomerTransformer;
import com.spike.customer.repo.CustomerRepo;

@Stateful
public class CustomerRepoImpl implements CustomerRepo {

	@PersistenceContext(unitName = "customer-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public List<CustomerDTO> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(customer);
		TypedQuery<Customer> q = entityManager.createQuery(cq);
		return CustomerTransformer.INSTANCE.toDTO(q.getResultList());
	}

	@Override
	public CustomerDTO list(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(customer);

		ParameterExpression<Long> p = cb.parameter(Long.class);
		cq.select(customer).where(cb.equal(customer.get("id"), p));

		TypedQuery<Customer> q = entityManager.createQuery(cq);
		q.setParameter(p, id);
		return CustomerTransformer.INSTANCE.toDTO(q.getSingleResult());
	}

	@Override
	public CustomerDTO create(Customer c) {
		entityManager.persist(c);
		return CustomerTransformer.INSTANCE.toDTO(c);
	}

	@Override
	public void delete(long id) {
		Customer c = entityManager.find(Customer.class, id);
		entityManager.remove(c);
	}

	@Override
	public CustomerDTO update(long id, String name, String lastname, int year) {
		Customer c = entityManager.find(Customer.class, id);
		c.setName(name);
		c.setLastname(lastname);
		c.setYear(year);
		entityManager.merge(c);
		return CustomerTransformer.INSTANCE.toDTO(c);
	}
}
