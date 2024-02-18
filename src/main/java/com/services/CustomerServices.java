package com.services;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.dao.CustomerDao;
import com.entity.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomerServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDao custDao;
	private HttpServletRequest req;
	private HttpServletResponse resp;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		custDao = new CustomerDao();
		req = request;
		resp = response;
	}

	public void listAllCustomer() throws ServletException, IOException {
		listAllCustomer(null);
	}

	public void listAllCustomer(String msg) throws ServletException, IOException {
		req.setAttribute("listCustomer", custDao.listAll());
		if (msg != null) {
			req.setAttribute("message", msg);
		}
		String filePath = "/admin/listCustomer.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}

	private void updateCustomerForm(Customer cust) {
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String addr1 = req.getParameter("adrline1");
		String addr2 = req.getParameter("adrline2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zipCode = req.getParameter("zipcode");
		String country = req.getParameter("country");

		if (email != null && !email.equals(""))
			cust.setEmail(email);
		cust.setFirstname(firstName);
		cust.setLastname(lastName);
		if (!password.isEmpty() && !password.equals(""))
			cust.setPassword(password);
		cust.setPhone(phone);
		cust.setAddressLine2(addr2);
		cust.setCity(city);
		cust.setCustomercol(zipCode);
		cust.setState(state);
		cust.setAddressLine1(addr1);
		cust.setCountry(country);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = req.getParameter("email");
		Customer cust = custDao.findByEmail(email);
		if (cust != null) {
			String msg = "Couldn't create new customer: this email " + email
					+ " is already registered with another customer.";
			listAllCustomer(msg);
			return;
		}
		Customer customer = new Customer();
		updateCustomerForm(customer);
		customer.setRegisterDate(new Date());

		custDao.create(customer);
		String msg = "Customer's account created successfully.";
		listAllCustomer(msg);
	}

	public void updateCustomer() throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("customerId"));
		String email = req.getParameter("email");
		Customer existCust = custDao.findByEmail(email);
		String msg = null;
		if (existCust != null && existCust.getCustomerId() != id) {
			msg = "Could't update customer, the email: " + email + " already registered with another customer.";
		} else {
			Customer cust = custDao.get(id);
			updateCustomerForm(cust);

			custDao.update(cust);
			msg = "Customer details has been updated successfully.";
		}
		listAllCustomer(msg);
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = req.getParameter("email");
		Customer cust = custDao.findByEmail(email);
		String msg = null;
		if (cust != null) {
			msg = "Account couldn't be created. This email " + email + " is already registered with another customer.";
		} else {
			Customer customer = new Customer();
			updateCustomerForm(customer);
			customer.setRegisterDate(new Date());

			custDao.create(customer);
			msg = "Your account created successfully. Thank You!<span style='font-size:20px;'>&#128522;</span><br/>"
					+ "<a href='login'> Click Here</a> to login";
		}
		req.setAttribute("message", msg);
		String path = "frontend/message.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(path);
		rqd.forward(req, resp);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(loginPage);
		rqd.forward(req, resp);
	}

	public void doLogin() throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Customer cust = custDao.checkLogin(email, password);

		if (cust == null) {
			String msg = "Login faild. Either email/password is incorrect.";
			req.setAttribute("message", msg);
			showLogin();
		} else {
			HttpSession session = req.getSession();
			Object requestUrl = session.getAttribute("requestURL");
			session.setAttribute("loggedCustomer", cust);

			if (requestUrl != null) {
				String reqUrl = (String) requestUrl;
				session.removeAttribute("requestURL");
				resp.sendRedirect(reqUrl);
			} else {
				showCustomerProfile();
			}
		}
	}

	public void showCustomerProfile() throws ServletException, IOException {
		req.setAttribute("message", "Customer Profile Page.");
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(profilePage);
		rqd.forward(req, resp);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		ListCountries();

		String profilePage = "frontend/edit_profile.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(profilePage);
		rqd.forward(req, resp);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer cust = (Customer) req.getSession().getAttribute("loggedCustomer");

		updateCustomerForm(cust);
		custDao.update(cust);
		showCustomerProfile();
	}

	public void editCustomer() throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		ListCountries();
		req.setAttribute("customer", custDao.get(id));
		String path = "/admin/customer_form.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(path);
		rqd.forward(req, resp);
	}

	public void ListCountries() {
		String[] countries = Locale.getISOCountries();
		Map<String, String> listCountry = new TreeMap();
		for (String it : countries) {
			Locale locale = new Locale("", it);
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();
			listCountry.put(name, code);
		}
		req.setAttribute("countries", listCountry);
		return;
	}

	public void newCustomer() throws ServletException, IOException {
		ListCountries();
		String filePath = "../admin/customer_form.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}

	public void showCustomerForm() throws ServletException, IOException {
		ListCountries();
		
		String filePath = "frontend/register_form.jsp";
		RequestDispatcher rqd = req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);

	}
}