package zfp.com.pmtest.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zfp.com.pmtest.entity.Customer;
import zfp.com.pmtest.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Set<Customer> getCustomers() {
		Set<Customer> customerSet = new HashSet<>();
		customerRepository.findAll().iterator().forEachRemaining(customerSet::add);
		return customerSet;
	}

	@Override
	public Customer findById(Long l) {
		 Optional<Customer> customerOptional = customerRepository.findById(l);

	        if (!customerOptional.isPresent()) {
	            throw new RuntimeException("Employee Not Found!");
	        }

	        return customerOptional.get();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		 return customerRepository.save(customer);
	    }
	}


