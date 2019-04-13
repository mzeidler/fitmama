package fitmama.service;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import fitmama.repo.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> findByUserId(Long userId, MeasurementType type) {
        return measurementRepository.findByUser_IdAndType(userId, type);
    }

    public List<Measurement> findByUserId(Long userId) {
        return measurementRepository.findByUser_Id(userId);
    }

    public Measurement save(Long userId, Measurement measurement) {
        return measurementRepository.saveAndFlush(measurement);
    }

    public void delete(Long measurementId) {
        measurementRepository.deleteById(measurementId);
    }
}
