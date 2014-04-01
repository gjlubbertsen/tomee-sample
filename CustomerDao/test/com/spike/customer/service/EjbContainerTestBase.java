package com.spike.customer.service;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class EjbContainerTestBase {
	
	private static EJBContainer container;

    @BeforeClass
    public static void setUpContainer() throws Exception {
        Properties p = new Properties();
        p.put("log4j.category.OpenEJB.cdi", "debug");
        p.put("customer-unit", "new://Resource?type=DataSource");
        p.put("customer-unit.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("customer-unit.JdbcUrl", "jdbc:hsqldb:mem:customerDB");
        p.put("customer-unit.openjpa.jdbc.DBDictionary", "hsql(SimulateLocking=true)");
        
        container = EJBContainer.createEJBContainer(p);
    }
    
    @AfterClass
    public static void teardownContainer() throws Exception {
        if (container != null) {
        	container.close();
        }
    }
    
    @Before
    public void inject() throws Exception {
    	container.getContext().bind("inject", this);
    }
}
