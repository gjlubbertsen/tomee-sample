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
import javax.ws.rs.core.Response;

import com.spike.customer.dto.CustomerDTO;
import com.spike.customer.service.CustomerService;

@Path("/")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CustomerController {
	
    @Inject
    private CustomerService customerService;
    
    @GET
    @Produces("text/html")
    public Response index() {
        return  Response.status(Response.Status.NOT_FOUND).entity(
                String.format("No actions on /")).build();
    }
    
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
    public void setSSN(@PathParam("id") long id, int ssn) {
        customerService.setSSN(id, ssn);
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
