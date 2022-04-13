package lt.ku.studentai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.studentai.entities.Student;
import lt.ku.studentai.services.GroupService;
import lt.ku.studentai.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudenController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	GroupService groupService;
	
	@GetMapping("/")
	public String getStudents(Model model) {
		model.addAttribute("students",studentService.getStudents());
		return "student_list";
	}
	
	@GetMapping("/new")
	public String newStudent(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("groups", groupService.getGroups());
		return "student_new";
	}
	
	@PostMapping("/new")
	public String storeStudent(
			@Valid
			@ModelAttribute 
			Student student, 
			BindingResult result,
			
			@RequestParam("groupId")
			Integer groupId,
			
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("groups", groupService.getGroups());
			return "student_new";
		}
		student.setGroup(groupService.getGroup(groupId));
		studentService.addStudent(student);
		
		return "redirect:/student/";
		
	}
}
