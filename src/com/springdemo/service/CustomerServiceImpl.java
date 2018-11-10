package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject dao
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {		
		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
				
		customerDao.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {		
		return customerDao.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		// use deleteCustomer method of CustomerDAO
		customerDao.deleteCustomer(customerId);
		
	}

}
