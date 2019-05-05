package fitmama.repo;

import fitmama.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import fitmama.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByUsername(String username);
}
