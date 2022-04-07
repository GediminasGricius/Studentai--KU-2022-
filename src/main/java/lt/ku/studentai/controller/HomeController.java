package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lt.ku.studentai.entities.Group;
import lt.ku.studentai.repositories.GroupRepository;
import lt.ku.studentai.services.GroupService;

@Controller
public class HomeController {
	@Autowired
	GroupService groupService;
	
	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("groups", groupService.getGroups());
		
		
		return "home";
	}

}
