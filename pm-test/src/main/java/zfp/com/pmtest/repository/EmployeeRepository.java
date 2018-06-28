package zfp.com.pmtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import zfp.com.pmtest.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	public Employee findByUserNameAndPassword(String userName, String password);
	
	/*@Query(value = "select e from Employee e where e.id not in(:id)")
	public List<Employee> findAllExceptOne(@Param("id") Long id);*/

}
