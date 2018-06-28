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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.entity.Project;
import zfp.com.pmtest.repository.EmployeeRepository;
import zfp.com.pmtest.repository.ProjectRepository;
import zfp.com.pmtest.services.EmployeeServiceImpl;
import zfp.com.pmtest.services.ProjectService;
import zfp.com.pmtest.services.ProjectServiceImpl;

@Controller
public class ProjectController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping("/projects")
	public String getProjects(Model model) {
		model.addAttribute("projects", projectService.getProjects());
		return "projects";
	}

	@RequestMapping("project/new")
	public String newProject(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("listEmployees", this.employeeService.getEmployees());
		return "project";
	}
	
	@GetMapping("/project/addTeam")
	public ModelAndView addTeam() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projects", projectRepository.findAll());
		mv.addObject("employees", employeeRepository.findAll());
		mv.setViewName("addTeam");
		return mv;
	}
	@RequestMapping("project/{id}/update")
	public String updateProject(@PathVariable String id, Model model) {
		model.addAttribute("project", projectService.findById(Long.valueOf(id)));
		model.addAttribute("employeesList", this.employeeService.getEmployees());
		return "project";
	}

	@RequestMapping("project/{id}/showTeam")
	public String showTeam(@PathVariable String id, Model model) {
		Project project = projectService.findById(Long.valueOf(id));
		Set<Employee> employees = project.getEmployees();
		model.addAttribute("project", project);
		model.addAttribute("employees", employees);
		return "projectEmployees";
	}

	@PostMapping("/projects/saveProject")
	public String saveOrUpdateProject(Model model, Project project) {
		projectService.saveProject(project);
		model.addAttribute("projects", projectService.getProjects());
		return "redirect:/projects/";
	}

	@RequestMapping("project/{id}/delete")
	public ModelAndView deleteProject(@PathVariable String id, ModelMap model) {
		projectService.deleteProject(Long.valueOf(id));
		model.addAttribute("projects", projectService.getProjects());
		return new ModelAndView("redirect:/projects", model);
	}

	@PostMapping("/project/saveProjectEmployees")
	public String saveProjectEmployees(@RequestParam(name = "project") Project project,
			@RequestParam(name = "employees") Set<Employee> employees, @RequestParam(name = "action") String action, Model model) {
		Set<Employee> l = project.getEmployees();
		if (action.equals("assign")) {
			l.addAll(employees);
		} else {
			l.removeAll(employees);
		}
		project.setEmployees(l);
		projectRepository.save(project);
		return "redirect:/projects/";
	}
	
	/*@PostMapping("/projects/saveTeam")
	public String saveOrUpdate(Model model, @RequestParam("id") Long projectId,
			@RequestParam("employees") Long employeeIds) {
		Project project = projectService.findById(projectId);
		Employee employee = employeeService.findById(employeeIds);
		Set<Employee> employees = new LinkedHashSet<Employee>();
		employees.add(employee);
		project.setEmployees(employees);
		projectService.saveProject(project);
		model.addAttribute("project", project);
		return "redirect:/projects/";
	}*/

}
