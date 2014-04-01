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
import javax.ws.rs.core.MediaType;

import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.service.CustomerService;

@Path("/api")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CustomerController {
	
    @Inject
    private CustomerService customerService;
    
    @Path("/customers")
    @POST
    public CustomerDTO create(CustomerDTO dto) {
        return customerService.create(dto);
    }

    @Path("/customers/{postId}")
    @GET
    public CustomerDTO list(@PathParam("postId") long customerId) {
        return customerService.list(customerId);
    }
    
    @Path("/customers")
    @GET
    public List<CustomerDTO> list() {
        return customerService.getCustomers();
    }
    
    @Path("/customers/{id}/SSN")
    @GET
    public int getSSN(@PathParam("id") long id) {
        return customerService.listSSN(id);
    }
    
    
    @Path("/customers/{id}/SSN")
    @PUT
    public int setSSN(@PathParam("id") long id, long ssn) {
        return customerService.setSSN(id, ssn);
    }
    // set SSN to Person

    @Path("/customers/{id}")
    @DELETE
    public void delete(@PathParam("id") long id) {
        customerService.delete(id);
    }

    @Path("/customers/{id}")
    @PUT
    public CustomerDTO update(@PathParam("id") long id,
                          @QueryParam("name") String name,
                          @QueryParam("lastname") String lastname,
                          @QueryParam("year") int year) {
        return customerService.update(id, name, lastname, year);
    }
}
