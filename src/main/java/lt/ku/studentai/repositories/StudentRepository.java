package lt.ku.studentai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.studentai.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
