package zfp.com.pmtest.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@PostMapping("/projects/saveTeam")
	public String saveOrUpdate(Model model, @RequestParam("id") Long projectId,
			@RequestParam("empId") Long employeeId)
	{
		Project project = projectService.findById(projectId);
		Employee employee = employeeService.findById(employeeId);
		Set<Project> projects = new LinkedHashSet<Project>();

		projects.add(project);
		
		employee.setProjects(projects);
		employeeService.saveEmployee(employee);
		model.addAttribute("project", project);
		model.addAttribute("employee", employee);
		return "redirect:/projects/";
		
	}

	/*
	 * @PostMapping("/projects/saveProject") public String saveOrUpdate(Model model,
	 * Project project) { projectService.saveProject(project);
	 * model.addAttribute("projects",projectService.getProjects()); return
	 * "redirect:/projects/"; }
	 */

	/*
	 * @RequestMapping(value = "/employee/projects/add", method =
	 * RequestMethod.POST) public String
	 * addEmployeeProject(@RequestParam("employeeId") int employeeId,
	 * 
	 * @RequestParam("projects") Set<Integer> projectIds) { EmployeeBO emp =
	 * this.employeeService.getEmployeeById(employeeId); Set<ProjectBO> projects =
	 * new LinkedHashSet<ProjectBO>(); if (emp.getProjects() != null &&
	 * emp.getProjects().size() > 0) { for (ProjectBO pro : emp.getProjects()) {
	 * projectIds.add(pro.getId()); } } for (Integer pId : projectIds) { ProjectBO p
	 * = this.projectService.getProjectById(pId); projects.add(p); }
	 * emp.setProjects(projects); this.employeeService.updateEmployee(emp);
	 * 
	 * return "redirect:/employeeProjects";
	 * 
	 * }
	 */
}
