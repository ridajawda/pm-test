package zfp.com.pmtest.services;


import java.util.Set;

import zfp.com.pmtest.entity.Project;


public interface ProjectService {

	Set<Project> getProjects();

	Project findById(Long id);

	Project saveProject(Project project);
}

