package zfp.com.pmtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zfp.com.pmtest.entity.Client;

@Repository
public interface ClientRepository extends  CrudRepository<Client, Long>{

}
