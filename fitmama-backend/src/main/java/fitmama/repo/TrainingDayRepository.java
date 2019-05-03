package fitmama.repo;

import fitmama.model.Menu;
import fitmama.model.MenuDay;
import fitmama.model.Training;
import fitmama.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {
    List<TrainingDay> findByTrainingAndDay(Training training, String day);
}
