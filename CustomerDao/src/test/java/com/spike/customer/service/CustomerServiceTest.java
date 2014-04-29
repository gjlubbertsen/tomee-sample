package com.spike.customer.service;

import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_FOR_SETTING_SSN;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_FOR_SETTING_SSN_ID;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_FOR_SETTING_SSN_NUMBER;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_UNKNOW_ID;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_WITHOUT_SSN_ID;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_WITH_SSN_ID;
import static com.spike.customer.service.CustomerServiceTest.TestRepo.CUSTOMER_WITH_SSN_NUMBER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.spike.customer.domain.Customer;
import com.spike.customer.dto.CustomerTransformer;
import com.spike.customer.repo.CustomerRepo;
import com.spike.customer.service.impl.CustomerServiceImpl;

@RunWith(Arquillian.class)
public class CustomerServiceTest {

	public static class TestRepo {
		
		public static long CUSTOMER_UNKNOW_ID = 10000;
		public static long CUSTOMER_WITHOUT_SSN_ID = 1;
		public static Customer CUSTOMER_WITHOUT_SSN = new Customer(CUSTOMER_WITHOUT_SSN_ID);
		public static long CUSTOMER_WITH_SSN_ID = 2;
		public static Customer CUSTOMER_WITH_SSN = new Customer(CUSTOMER_WITH_SSN_ID);
		public static int CUSTOMER_WITH_SSN_NUMBER = 10002; 
		static 
		{
			CUSTOMER_WITH_SSN.setSocialSecurityNr(CUSTOMER_WITH_SSN_NUMBER);
		}
		public static long CUSTOMER_FOR_SETTING_SSN_ID = 3;
		public static Customer CUSTOMER_FOR_SETTING_SSN = Mockito.mock(Customer.class);
		public static int CUSTOMER_FOR_SETTING_SSN_NUMBER = 10003; 
		
		@Produces
		public static CustomerRepo mockedRepo = Mockito.mock(CustomerRepo.class);
		static {
			 when(mockedRepo.list(CUSTOMER_WITHOUT_SSN_ID)).thenReturn(CUSTOMER_WITHOUT_SSN);
			 when(mockedRepo.list(CUSTOMER_WITH_SSN_ID)).thenReturn(CUSTOMER_WITH_SSN);
			 when(mockedRepo.list(CUSTOMER_FOR_SETTING_SSN_ID)).thenReturn(CUSTOMER_FOR_SETTING_SSN);
		}
		
	}
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Inject
	private CustomerService cs;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(CustomerServiceImpl.class, CustomerTransformer.class, TestRepo.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void list_ssn_on_not_existing_customer()  {
		exceptions.expect(EntityNotFoundException.class);
		cs.listSSN(CUSTOMER_UNKNOW_ID);
	}
	
	@Test
	public void list_ssn_on_existing_customer_with_out_ssn_set()  {
		exceptions.expect(SSNNotSetException.class);
		cs.listSSN(CUSTOMER_WITHOUT_SSN_ID);
	}
	
	@Test
	public void list_ssn_on_existing_customer_with_ssn_set_returns_ssn()  {
		assertEquals(CUSTOMER_WITH_SSN_NUMBER, cs.listSSN(CUSTOMER_WITH_SSN_ID));
	}
	
	@Test
	public void set_ssn_on_not_existing_customer()  {
		exceptions.expect(EntityNotFoundException.class);
		cs.setSSN(CUSTOMER_UNKNOW_ID, 0);
	}
	
	@Test
	public void set_ssn_on_existing_customer_with_out_ssn_set()  {
		exceptions.expect(SSNNotSetException.class);
		cs.setSSN(CUSTOMER_WITHOUT_SSN_ID, 0);
	}
	
	@Test
	public void set_ssn_on_existing_customer_with_ssn_set_returns_ssn()  {
		cs.setSSN(CUSTOMER_FOR_SETTING_SSN_ID, CUSTOMER_FOR_SETTING_SSN_NUMBER);
		verify(CUSTOMER_FOR_SETTING_SSN).setSocialSecurityNr(CUSTOMER_FOR_SETTING_SSN_NUMBER);
	}
	

}
