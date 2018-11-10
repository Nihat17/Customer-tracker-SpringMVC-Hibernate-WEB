package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override	
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName",
											Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		// get the current session from session factory
		Session session = sessionFactory.getCurrentSession();
		
		// get the customer from database
		Customer customer = session.get(Customer.class, id);
		
		// return the customer
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		// create a delete query
		Query query = session.createQuery(
				"Delete from Customer c where c.id = :customerId");
		
		// set the parameter
		query.setParameter("customerId", customerId);
		
		// execute the query
		query.executeUpdate();
	}

}





