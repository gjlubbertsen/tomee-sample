package com.spike.customer.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.spike.customer.dao.CustomerDao;
import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.dto.CustomerTransformer;

@Path("/api/Customer")
@Produces({"text/xml", "application/json"})
public class CustomerController {
	
    @Inject
    private CustomerDao CustomerDao;
    
    @Path("/create")
    @PUT
    public CustomerDTO create(CustomerDTO dto) {
        return CustomerDao.create(CustomerTransformer.INSTANCE.toBO(dto));
    }

    @Path("/list/{postId}")
    @GET
    public List<CustomerDTO> list(@PathParam("postId") long customerId) {
        return CustomerDao.list(customerId);
    }
    
    @Path("/list/")
    @GET
    public List<CustomerDTO> list() {
        return CustomerDao.listAll();
    }
    
    // set SSN to Person

    @Path("/delete/{id}")
    @DELETE
    public void delete(@PathParam("id") long id) {
        CustomerDao.delete(id);
    }

    @Path("/update/{id}")
    @POST
    public CustomerDTO update(@PathParam("id") long id,
                          @QueryParam("name") String name,
                          @QueryParam("lastname") String lastname,
                          @QueryParam("year") int year) {
        return CustomerDao.update(id, name, lastname, year);
    }
}
