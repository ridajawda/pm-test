package zfp.com.pmtest.services;


import java.util.Set;

import zfp.com.pmtest.entity.Employee;


public interface EmployeeService {

	Set<Employee> getEmployees();

	Employee findById(Long id);

	Employee saveEmployee(Employee employee);
}

