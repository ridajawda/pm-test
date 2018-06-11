package zfp.com.pmtest.repository;

import org.springframework.data.repository.CrudRepository;

import zfp.com.pmtest.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	

}
