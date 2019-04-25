package fitmama.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import fitmama.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
