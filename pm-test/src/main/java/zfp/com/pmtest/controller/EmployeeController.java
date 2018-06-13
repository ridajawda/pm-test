package zfp.com.pmtest.controller;

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
import org.springframework.web.servlet.ModelAndView;

import zfp.com.pmtest.entity.Customer;
import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.services.EmployeeService;
import zfp.com.pmtest.services.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	/*@RequestMapping("/listEmployees")
	public String getEmployees(Model model) {
		model.addAttribute("employees", employeeService.getEmployees());
		return "employees";
	}*/
	
	@RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
	public ModelAndView employees() {
		ModelAndView mv = new ModelAndView();
		//mv.addObject("employee", new EmployeeBO());
		mv.addObject("employees", this.employeeService.getEmployees());
		mv.setViewName("employees");
		return mv;
	}

	@RequestMapping("/employees/new")
	public String newEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "/employee";
	}


	@RequestMapping("employee/{id}/update")
	public String updateEmployee(@PathVariable String id, Model model) {
		model.addAttribute("employee", employeeService.findById(Long.valueOf(id)));
		return "employee";
	}

	@PostMapping("/employees/saveEmployee")
	public ModelAndView saveOrUpdate(ModelMap model, Employee employee) {
		 employeeService.saveEmployee(employee);
		 model.addAttribute("employees", employeeService.getEmployees());
		 return new ModelAndView("redirect:/listEmployees", model);	
	}
	
	
	}
