package zfp.com.pmtest.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.entity.Project;
import zfp.com.pmtest.services.EmployeeServiceImpl;
import zfp.com.pmtest.services.ProjectService;
import zfp.com.pmtest.services.ProjectServiceImpl;

@Controller
public class ProjectController {

	@Autowired
	private EmployeeServiceImpl employeeService;

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
		model.addAttribute("listEmployees", this.employeeService.getEmployees());
		return "project";
	}

	@RequestMapping("project/{id}/update")
	public String updateProject(@PathVariable String id, Model model) {
		model.addAttribute("project", projectService.findById(Long.valueOf(id)));
		model.addAttribute("employeesList", this.employeeService.getEmployees());
		return "project";
	}

	@RequestMapping("project/{id}/addTeam")
	public String addProjectTeam(@PathVariable String id, Model model) {
		model.addAttribute("project", projectService.findById(Long.valueOf(id)));
		model.addAttribute("employeesList", this.employeeService.getEmployees());
		return "addTeam";
	}
	
	@PostMapping("/projects/saveProject")
	public String saveOrUpdateProject(Model model, Project project) {
		projectService.saveProject(project);
		model.addAttribute("projects",projectService.getProjects());
		return "redirect:/projects/";
	}
	
	@RequestMapping("project/{id}/delete")
	public ModelAndView deleteProject(@PathVariable String id, ModelMap model) {
		projectService.deleteProject(Long.valueOf(id));
		 model.addAttribute("projects", projectService.getProjects());
		 return new ModelAndView("redirect:/projects", model);	
	}

	@PostMapping("/projects/saveTeam")
	public String saveOrUpdate(Model model, @RequestParam("id") Long projectId,
			@RequestParam("employees") Long employeeIds)
	{
		Project project = projectService.findById(projectId);
		Employee employee = employeeService.findById(employeeIds);
		Set<Employee> employees = new LinkedHashSet<Employee>();
		employees.add(employee);
		project.setEmployees(employees);
		projectService.saveProject(project);
		model.addAttribute("project", project);
		return "redirect:/projects/";
		}
	
	}
