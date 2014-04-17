package com.spike.customer; 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spike.customer.domain.Customer;
import com.spike.customer.repo.CustomerRepo;

@WebServlet("/HelloCustomer")
public class HelloCustomer extends HttpServlet {

	@Inject
	private CustomerRepo doa;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Hello World");
		List<Customer> list = doa.listAll();
		for (Customer customer : list) {
			out.println(customer);
		}
		out.println("END");
	}
}
