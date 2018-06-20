package zfp.com.pmtest.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import zfp.com.pmtest.entity.Project;
import zfp.com.pmtest.repository.ProjectRepository;

@Component
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	
	@Override
	public Set<Project> getProjects() {
		Set<Project> projectSet = new HashSet<>();
		projectRepository.findAll().iterator().forEachRemaining(projectSet::add);
		return projectSet;
	}

	@Override
    public Project findById(Long l) {

        Optional<Project> projectOptional = projectRepository.findById(l);

        if (!projectOptional.isPresent()) {
            throw new RuntimeException("Project Not Found!");
        }

        return projectOptional.get();
    }

	@Override
	@Transactional
	public Project saveProject(Project project) {
		
		return  projectRepository.save(project);
				
	}
	@Override
	@Transactional
	public void deleteProject(Long id) {

		projectRepository.deleteById(id);

	}
}
