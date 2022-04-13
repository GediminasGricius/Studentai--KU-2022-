package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lt.ku.studentai.entities.Group;
import lt.ku.studentai.entities.Student;
import lt.ku.studentai.services.GroupService;
import lt.ku.studentai.services.StudentService;

@Controller
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	GroupService groupService;
	
	@GetMapping("/")
	public String home() {
		
		//Student s=new Student("Jonas", "Jonaitis", "jonas@gmail.com");
		//studentService.addStudent(s);
		
		Student s=studentService.getStudent(1);
		Group g=groupService.getGroup(3);
		
		System.out.println(s);
		System.out.println(g);
		
		
		/*
		s.setGroup(g);
		studentService.updateStudent(s);
		*/
		
		
		return "home";
	}
}
