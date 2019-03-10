package fitmama.service;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import fitmama.model.UserGroup;
import fitmama.repo.MeasurementRepository;
import fitmama.repo.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private Scheduler dbScheduler;

    public Flux<Measurement> findByUserId(Long userId, MeasurementType type) {
        return Flux.fromIterable(measurementRepository.findByUser_IdAndType(userId, type)).publishOn(dbScheduler);
    }

    public Flux<Measurement> findByUserId(Long userId) {
        return Flux.fromIterable(measurementRepository.findByUser_Id(userId)).publishOn(dbScheduler);
    }
}
