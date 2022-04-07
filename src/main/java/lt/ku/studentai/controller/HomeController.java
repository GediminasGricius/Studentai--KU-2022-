package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/groupNew")
	public String groupNew(Model model) {
		return "group_new";
	}
	
	@PostMapping("/groupNew")
	public String addGroup(@RequestParam("name") String name, @RequestParam("year") Integer year) {
		Group g=new Group(name,year);
		groupService.addGroup(g);
		return "redirect:/";
	}
	
	@GetMapping("/groupUpdate")
	public String groupNew(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("group", groupService.getGroup(id));
		return "group_update";
	}
	
	@PostMapping("/groupUpdate")
	public String groupUpdate(@ModelAttribute Group g) {
		groupService.updateGroup(g);
		return "redirect:/";
	}

}
