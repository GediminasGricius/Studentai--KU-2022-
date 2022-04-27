package lt.ku.studentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lt.ku.studentai.entities.Group;
import lt.ku.studentai.entities.Student;
import lt.ku.studentai.entities.User;
import lt.ku.studentai.services.GroupService;
import lt.ku.studentai.services.StudentService;
import lt.ku.studentai.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		
		//User user=new User("gediminas","LabasRytas","g.gricius@ituostas.lt","admin");
		//userService.addUser(user);
		
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
