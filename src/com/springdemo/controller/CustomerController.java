package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	
	// add a new method for showFormForAdd
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		
		// create model attribute to bind form data		
		Customer customer = new Customer();
		
		// add customer to the model
		theModel.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer){
	
		// save the customer using service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
									Model theModel){
		
		// first get the customer from service
		Customer theCustomer = customerService.getCustomer(id);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deletCustomer(@RequestParam("customerId") int customerId,
			Model theModel){
		
		// need to use deleteCUstomer method of CustomerService
		customerService.deleteCustomer(customerId);
		
		
		// return to the customer list page
		return "redirect:/customer/list";
	}
	
}
