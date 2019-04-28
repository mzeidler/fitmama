package fitmama.repo;

import fitmama.model.Menu;
import fitmama.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    public List<Training> findAllByOrderByIdAsc();
}
