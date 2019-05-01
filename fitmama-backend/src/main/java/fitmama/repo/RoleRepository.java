package fitmama.repo;

import fitmama.model.Role;
import fitmama.model.RoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleKey(RoleKey key);
}
