package zfp.com.pmtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zfp.com.pmtest.entity.Customer;
import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.services.CustomerService;

@Controller	
public class CustomerController {
	
	 @Autowired
	    CustomerService customerService;

	    @RequestMapping(value="/customers")
	    public String notesList(Model model) {
	        model.addAttribute("customers", customerService.getCustomers());
	        return "customers";
	    }

	    @GetMapping(value={"customer/{id}/update"})
	    public String updateCustomer(Model model, @PathVariable(required = false, name = "id") Long id) {
	        if (null != id) {
	            model.addAttribute("customer", customerService.findById(id));
	        } else {
	            model.addAttribute("customer", new Customer());
	        }
	        return "customer";
	    }
	    
	    @GetMapping(value={"customers/new"})
	    public String addCustomer(Model model) {
	    	 model.addAttribute("customer", new Customer()); 
	        return "customer";
	    }

	    @PostMapping(value="/customers/customerAdd")
	    public String notesEdit(Model model, Customer customer) {
	    	customerService.saveCustomer(customer);
	        model.addAttribute("customers", customerService.getCustomers());
	        return "customer";
	    }

	    

}
