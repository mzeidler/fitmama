package fitmama.repo;

import fitmama.model.Menu;
import fitmama.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    public List<Menu> findAllByOrderByIdAsc();
}
