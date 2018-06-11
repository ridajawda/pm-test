package zfp.com.pmtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zfp.com.pmtest.entity.Project;
import zfp.com.pmtest.services.ProjectService;
import zfp.com.pmtest.services.ProjectServiceImpl;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/projects")
	public String getProjects(Model model) {
		model.addAttribute("projects", projectService.getProjects());
		return "projects";
	}

	@RequestMapping("projects/new")
	public String newProject(Model model) {
		model.addAttribute("project", new Project());
		return "project";
	}

	@RequestMapping("project/{id}/update")
	public String updateProject(@PathVariable String id, Model model) {
		model.addAttribute("project", projectService.findById(Long.valueOf(id)));
		return "project";
	}

	@PostMapping("saveProject")
	public String saveOrUpdate(Model model, Project project) {
		projectService.saveProject(project);
		model.addAttribute("projects",projectService.getProjects());
		return "redirect:/projects/";
	}

	}
