package fitmama.controller;

import fitmama.model.Measurement;
import fitmama.model.MeasurementType;
import fitmama.model.User;
import fitmama.service.MeasurementService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementController {

	@Autowired
	private MeasurementService service;

	@GetMapping("/measurements/{userid}/{type}")
	public List<Measurement> findByUserId(@PathVariable Long userid, @PathVariable MeasurementType type) {
		return service.findByUserId(userid, type);
	}

	@GetMapping("/measurements/{userid}")
	public List<Measurement> findByUserId(@PathVariable Long userid) {
		return service.findByUserId(userid);
	}

	@PostMapping("/measurements/{userid}/add")
	public Measurement save(@PathVariable Long userid, @RequestBody Measurement measurement) {
		return service.save(userid, measurement);
	}

	@DeleteMapping("/measurements/{measurementId}/remove")
	public void delete(@PathVariable Long measurementId) {
		service.delete(measurementId);
	}
}
