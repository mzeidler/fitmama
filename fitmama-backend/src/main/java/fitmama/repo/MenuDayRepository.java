package fitmama.repo;

import fitmama.model.Menu;
import fitmama.model.MenuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDayRepository extends JpaRepository<MenuDay, Long> {

}
