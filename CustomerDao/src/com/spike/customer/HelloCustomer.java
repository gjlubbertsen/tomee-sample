package com.spike.customer; // Always use packages. Never use default package.

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spike.customer.dao.CustomerDao;
import com.spike.customer.dto.CustomerDTO;

/**
 * Very simplistic servlet that generates plain text. Uses the @WebServlet
 * annotation that is supported by Tomcat 7 and other servlet 3.0 containers.
 * 
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF 2.x, Ajax, jQuery,
 * GWT, Spring, Hibernate/JPA, Hadoop, and Java programming</a>.
 */

@WebServlet("/hello")
public class HelloCustomer extends HttpServlet {

	@Inject
	private CustomerDao doa;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Hello World");
		List<CustomerDTO> list = doa.listAll();
		for (CustomerDTO customer : list) {
			out.println(customer);
		}
		out.println("END");
	}
}
