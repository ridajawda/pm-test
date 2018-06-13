package zfp.com.pmtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zfp.com.pmtest.entity.Consultant;
import zfp.com.pmtest.services.ConsultantService;

@Controller
public class ConsultantController {

	@Autowired
	private ConsultantService consultantService;

	@RequestMapping("/consultants")
	public String getConsultants(Model model) {
		model.addAttribute("consultants", consultantService.getConsultants());
		return "consultants";
	}

	@RequestMapping("consultants/new")
	public String newConsultant(Model model) {
		model.addAttribute("consultant", new Consultant());
		return "consultant";
	}

	@RequestMapping("consultant/{id}/update")
	public String updateConsultant(@PathVariable String id, Model model) {
		model.addAttribute("consultant", consultantService.findById(Long.valueOf(id)));
		return "consultant";
	}

	@PostMapping("/consultants/saveConsultant")
	public String saveOrUpdate(Model model, Consultant consultant) {
		consultantService.saveConsultant(consultant);
		model.addAttribute("consultants",consultantService.getConsultants());
		return "redirect:/consultants/";
	}
}