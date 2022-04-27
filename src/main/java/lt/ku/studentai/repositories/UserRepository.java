package lt.ku.studentai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.studentai.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

}
