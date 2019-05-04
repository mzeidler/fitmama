package fitmama.service;

import fitmama.model.Measurement;
import fitmama.model.User;
import fitmama.repo.MeasurementRepository;
import fitmama.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Measurement> findByUserId(Long userId) {
        User user = getUser(userId);
        if (user != null) {
            return measurementRepository.findByUser(user);
        }
        return new ArrayList<>();
    }

    public Measurement save(Long userId, Measurement measurement) {
        User user = getUser(userId);
        if (user != null) {
            measurement.setUser(user);
            return measurementRepository.saveAndFlush(measurement);
        }
        return measurement;
    }

    public Measurement update(Measurement measurement) {
        Measurement measurementDB = getMeasurement(measurement.getId());
        if (measurementDB != null) {
            measurementDB.setDay(measurement.getDay());
            measurementDB.setValue1(measurement.getValue1());
            measurementDB.setValue2(measurement.getValue2());
            measurementDB.setValue3(measurement.getValue3());
            measurementDB.setValue4(measurement.getValue4());
            measurementDB.setValue5(measurement.getValue5());
            measurementDB.setValue6(measurement.getValue6());
            measurementDB.setValue7(measurement.getValue7());
            measurementDB.setValue8(measurement.getValue8());
            return measurementRepository.saveAndFlush(measurementDB);
        }
        return measurementDB;
    }

    public void delete(Long measurementId) {
        measurementRepository.deleteById(measurementId);
    }

    public User getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.isPresent() ? userOpt.get() : null;
    }

    public Measurement getMeasurement(Long id) {
        Optional<Measurement> measurementOpt = measurementRepository.findById(id);
        return measurementOpt.isPresent() ? measurementOpt.get() : null;
    }

}
