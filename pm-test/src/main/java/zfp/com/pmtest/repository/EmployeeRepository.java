package zfp.com.pmtest.repository;

import org.springframework.data.repository.CrudRepository;

import zfp.com.pmtest.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	

}
