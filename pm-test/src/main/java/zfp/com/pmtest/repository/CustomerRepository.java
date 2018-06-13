package zfp.com.pmtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zfp.com.pmtest.entity.Customer;

@Repository
public interface CustomerRepository extends  CrudRepository<Customer, Long>{

}
