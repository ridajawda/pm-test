package zfp.com.pmtest.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.repository.EmployeeRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> employeeSet = new HashSet<>();
		employeeRepository.findAll().iterator().forEachRemaining(employeeSet::add);
		return employeeSet;
	}

	@Override
    public Employee findById(Long l) {

        Optional<Employee> employeeOptional = employeeRepository.findById(l);

        if (!employeeOptional.isPresent()) {
            throw new RuntimeException("Employee Not Found!");
        }

        return employeeOptional.get();
    }

	@Override
	@Transactional
	public Employee saveEmployee(Employee employee) {
		
		return  employeeRepository.save(employee);
		
		
	}
}
