package zfp.com.pmtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zfp.com.pmtest.entity.Client;
import zfp.com.pmtest.services.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping("/clients")
	public String getClients(Model model) {
		model.addAttribute("clients", clientService.getClients());
		return "clients";
	}

	@RequestMapping("clients/new")
	public String newClient(Model model) {
		model.addAttribute("client", new Client());
		return "client";
	}

	@RequestMapping("client/{id}/update")
	public String updateClient(@PathVariable String id, Model model) {
		model.addAttribute("client", clientService.findById(Long.valueOf(id)));
		return "client";
	}

	@PostMapping("/clients/saveClient")
	public String saveOrUpdate(Model model, Client client) {
		clientService.saveClient(client);
		model.addAttribute("clients",clientService.getClients());
		return "redirect:/clients/";
	}
}