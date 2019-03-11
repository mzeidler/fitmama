package fitmama.controller;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import fitmama.model.User;
import fitmama.service.MeasurementService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MeasurementController {

	// TEST2
	@Autowired
	private MeasurementService service;

	@GetMapping("/measurements/{userid}/{type}")
	public Flux<Measurement> findByUserId(@PathVariable Long userid, @PathVariable MeasurementType type) {
		return service.findByUserId(userid, type);
	}

	@GetMapping("/measurements/{userid}")
	public Flux<Measurement> findByUserId(@PathVariable Long userid) {
		return service.findByUserId(userid);
	}

	@PostMapping("/measurements/{userid}/add")
	public Mono<Measurement> save(@PathVariable Long userid, @RequestBody Measurement measurement) {
		return service.save(userid, measurement);
	}

	@DeleteMapping("/measurements/{measurementId}/remove")
	public void delete(@PathVariable Long measurementId) {
		service.delete(measurementId);
	}
}
