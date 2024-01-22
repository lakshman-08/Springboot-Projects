package restfulapi.project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restfulapi.project.springboot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByEmail(String email);
}
