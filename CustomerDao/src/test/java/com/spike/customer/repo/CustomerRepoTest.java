package com.spike.customer.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.spike.customer.domain.Customer;
import com.spike.junit.ejb.EjbContainerTestBase;

@ManagedBean
public class CustomerRepoTest extends EjbContainerTestBase {

	@Inject
	private CustomerRepo repo;

	@Before
	public void setup() {
		clearAllCustomers();
	}

	@Test
	public void list_all_should_return_four() {
		createFewCustomers(4);
		assertEquals(4, repo.listAll().size());
	}

	@Test
	public void list_specific_customer_should_return_it() {
		createFewCustomers(4);
		Customer c = repo.create(new Customer("BBBB", "b", 2003));
		assertEquals("BBBB", repo.list(c.getId()).getName());
	}

	@Test
	public void delete_specific_customer_test() {
		createFewCustomers(4);
		Customer c = repo.create(new Customer("BBBB", "b", 2003));
		repo.delete(c.getId());
		assertNull(repo.list(c.getId()));
		assertEquals(4, repo.listAll().size());
	}

	@Test
	public void update_specific_customer_test() {
		Customer c = repo.create(new Customer("BBBB", "b", 2003));
		repo.update(c.getId(), "AA", "b", 2003);
		assertEquals("AA", repo.list(c.getId()).getName());
	}

	private void createFewCustomers(int ammount) {
		for (int i = 0; i < ammount; i++) {
			repo.create(new Customer("a", "b", 2003));
		}
	}

	private void clearAllCustomers() {
		for (Customer itr : repo.listAll()) {
			repo.delete(itr.getId());
		}
	}

}
