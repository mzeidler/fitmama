package fitmama.repo;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByUser_Id(Long userId);
    List<Measurement> findByUser_IdAndType(Long userId, MeasurementType type);
}
