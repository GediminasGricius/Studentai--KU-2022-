package lt.ku.studentai.controller.rest;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.ku.studentai.classes.ErrorResponse;
import lt.ku.studentai.entities.Group;
import lt.ku.studentai.services.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupRestController {
	
	@Autowired
	private GroupService groupService;
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<ErrorResponse> inputException(ConstraintViolationException e){
		return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ErrorResponse> unknownError(){
		return new ResponseEntity<>(new ErrorResponse("Unknown error"), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@GetMapping("/")
	public List<Group> groups() {
		return groupService.getGroups();
	}
	@CrossOrigin
	@GetMapping("/{id}")
	public Group group(@PathVariable Integer id) {
		return groupService.getGroup(id);
	}
	@CrossOrigin
	@PostMapping("/")
	public Group newGroup(
			@RequestParam("name") String name,
			@RequestParam("year") Integer year
			) {
		Group g=new Group(name, year);
		g=groupService.addGroup(g);
		return g;
	}
	
}
