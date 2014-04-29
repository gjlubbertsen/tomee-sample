package com.spike.customer.repo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.spike.customer.domain.Customer;
import com.spike.customer.repo.CustomerRepo;

@Stateless
public class CustomerRepoImpl implements CustomerRepo {

	@PersistenceContext(unitName = "customer-unit")
	private EntityManager entityManager;
	
	public CustomerRepoImpl() {
	}

	@Override
	public List<Customer> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(customer);
		TypedQuery<Customer> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public Customer list(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);

		ParameterExpression<Long> p = cb.parameter(Long.class);
		cq.select(customer).where(cb.equal(customer.get("id"), p));

		TypedQuery<Customer> q = entityManager.createQuery(cq);
		q.setParameter(p, id);
		
	    List<Customer> results = q.getResultList();
        if (results.isEmpty()) {
        	return null;
        }
        else if (results.size() == 1) {
        	return results.get(0);
        }
        throw new NonUniqueResultException();
	}

	@Override
	public Customer create(Customer c) {
		entityManager.persist(c);
		return c;
	}

	@Override
	public void delete(long id) {
		Customer c = entityManager.find(Customer.class, id);
		entityManager.remove(c);
	}

	@Override
	public Customer update(long id, String name, String lastname, int year) {
		Customer c = entityManager.find(Customer.class, id);
		c.setName(name);
		c.setLastname(lastname);
		c.setYear(year);
		entityManager.merge(c);
		return c;
	}

	@Override
	public Customer update(Customer c) {
		entityManager.merge(c);
		return c;
	}
}
