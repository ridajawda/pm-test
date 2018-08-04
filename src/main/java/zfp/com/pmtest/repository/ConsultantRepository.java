package zfp.com.pmtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zfp.com.pmtest.entity.Client;
import zfp.com.pmtest.entity.Consultant;

@Repository
public interface ConsultantRepository extends  CrudRepository<Consultant, Long>{

}
