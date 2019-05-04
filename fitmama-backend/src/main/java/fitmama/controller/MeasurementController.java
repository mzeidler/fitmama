package fitmama.controller;

import fitmama.model.Measurement;
import fitmama.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MeasurementController {

	@Autowired
	private MeasurementService measurementService;

	@GetMapping("/api/measurements/{userid}")
	public List<Measurement> findByUserId(@PathVariable Long userid) {
		return measurementService.findByUserId(userid);
	}

	@PostMapping("/api/measurements/add/{userid}")
	public Measurement save(@PathVariable Long userid, @RequestBody Measurement measurement) {
		return measurementService.save(userid, measurement);
	}

	@PostMapping("/api/measurements/update")
	public Measurement update(@RequestBody Measurement measurement) {
		return measurementService.update(measurement);
	}

	@DeleteMapping("/api/measurements/delete/{measurementId}")
	public void delete(@PathVariable Long measurementId) {
		measurementService.delete(measurementId);
	}
}
