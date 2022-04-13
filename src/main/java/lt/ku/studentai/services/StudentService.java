package lt.ku.studentai.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.studentai.entities.Student;
import lt.ku.studentai.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public Student getStudent(Integer id) {
		return studentRepository.getById(id);
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public Student updateStudent(Student student) {
		Student old=this.getStudent(student.getId());
		old.setName(student.getName());
		old.setSurname(student.getSurname());
		old.setEmail(student.getEmail());
		old.setGroup(student.getGroup());
		studentRepository.save(old);
		return old;
	}
	
	public void deleteStudent(Integer id) {
		studentRepository.deleteById(id);
	}
	
	

}
