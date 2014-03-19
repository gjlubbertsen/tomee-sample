package com.spike.customer.dao;

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

@Stateful
public class CustomerDao {

	@PersistenceContext(unitName = "customer-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public List<CustomerDTO> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(customer);
		TypedQuery<Customer> q = entityManager.createQuery(cq);
		return CustomerTransformer.INSTANCE.toDTO(q.getResultList());
	}

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

	public CustomerDTO create(Customer c) {
		entityManager.persist(c);
		return CustomerTransformer.INSTANCE.toDTO(c);
	}

	public void delete(long id) {
		Customer c = entityManager.find(Customer.class, id);
		entityManager.remove(c);
	}

	public CustomerDTO update(long id, String name, String lastname, int year) {
		Customer c = entityManager.find(Customer.class, id);
		c.setName(name);
		c.setLastname(lastname);
		c.setYear(year);
		entityManager.merge(c);
		return CustomerTransformer.INSTANCE.toDTO(c);
	}

}
