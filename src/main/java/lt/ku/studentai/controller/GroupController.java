package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lt.ku.studentai.entities.Group;
import lt.ku.studentai.repositories.GroupRepository;
import lt.ku.studentai.services.FileStorageService;
import lt.ku.studentai.services.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@Autowired
	FileStorageService fileStorage;
	
	@GetMapping("/")  
	public String home(Model model) {
		model.addAttribute("groups", groupService.getGroups());
		return "group_lis";
	}
	
	@GetMapping("/new")  
	public String groupNew(Model model) {
		return "group_new";
	}
	
	@PostMapping("/new")
	public String addGroup(	@RequestParam("name") String name, 
							@RequestParam("year") Integer year, 
							@RequestParam("agreement") MultipartFile agreement) {
		Group g=new Group(name,year, agreement.getOriginalFilename());
		g=groupService.addGroup(g);
		fileStorage.store(agreement,g.getId().toString());
		return "redirect:/group/";
	}
	
	@GetMapping("/update/{id}")
	public String groupNew(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("group", groupService.getGroup(id));
		return "group_update";
	}
	
	@PostMapping("/update/{id}")
	public String groupUpdate(@PathVariable("id") Integer id, @ModelAttribute Group g) {
		groupService.updateGroup(g);
		return "redirect:/group/";
	}
	
	@GetMapping("/delete/{id}")
	public String groupDelete(@PathVariable("id") Integer id) {
		groupService.deleteGroup(id);
		return "redirect:/group/";
	}
	
	@GetMapping("/agreement/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getAgreement(@PathVariable Integer id) {
		Resource file=fileStorage.loadFile(id.toString());
		Group g=groupService.getGroup(id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+g.getFileName()+"\"")
				.body(file);
	}

}
