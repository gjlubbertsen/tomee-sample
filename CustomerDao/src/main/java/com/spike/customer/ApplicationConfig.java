package com.spike.customer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.spike.customer.controller.CustomerController;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
	
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(CustomerController.class,
				HelloCustomer.class));
	}
}
