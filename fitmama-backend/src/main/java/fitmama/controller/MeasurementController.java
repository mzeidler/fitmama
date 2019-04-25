package fitmama.controller;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import fitmama.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementController {

	@Autowired
	private MeasurementService service;

	@GetMapping("/api/measurements/{userid}/{type}")
	public List<Measurement> findByUserId(@PathVariable Long userid, @PathVariable MeasurementType type) {
		return service.findByUserId(userid, type);
	}

	@GetMapping("/api/measurements/{userid}")
	public List<Measurement> findByUserId(@PathVariable Long userid) {
		return service.findByUserId(userid);
	}

	@PostMapping("/api/measurements/{userid}/add")
	public Measurement save(@PathVariable Long userid, @RequestBody Measurement measurement) {
		return service.save(userid, measurement);
	}

	@DeleteMapping("/api/measurements/{measurementId}/remove")
	public void delete(@PathVariable Long measurementId) {
		service.delete(measurementId);
	}
}
