package zfp.com.pmtest.services;

import java.util.Set;

import zfp.com.pmtest.entity.Customer;

public interface CustomerService {
	
	Set<Customer> getCustomers();

	Customer findById(Long id);

	Customer saveCustomer(Customer customer);

}
