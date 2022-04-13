package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.ku.studentai.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudenController {
	@Autowired
	StudentService studentService;
	
	@GetMapping("/")
	public String getStudents(Model model) {
		model.addAttribute("students",studentService.getStudents());
		
		return "student_list";
	}
	

}
