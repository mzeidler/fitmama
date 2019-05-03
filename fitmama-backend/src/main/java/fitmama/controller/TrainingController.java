package fitmama.controller;

import fitmama.model.DayContent;
import fitmama.model.Training;
import fitmama.model.TrainingDay;
import fitmama.model.Trainings;
import fitmama.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrainingController {


    @Autowired
    private TrainingService trainingService;

    @GetMapping("/api/trainings")
    public List<Training> findAll() {
        return trainingService.findAll();
    }

    @GetMapping("/api/trainings/{id}")
    public Training find(@PathVariable Long id) {
        return trainingService.getTraining(id);
    }

    @GetMapping("/api/trainings/short")
    public Trainings findAllShort() {
        return new Trainings(trainingService.findAll());
    }

    @PostMapping("/api/trainings/add")
    public Training add(@RequestBody Training training) {
        return trainingService.add(training);
    }

    @PostMapping("/api/trainings/update")
    public Training update(@RequestBody Training training) {
        return trainingService.update(training);
    }

    @DeleteMapping("/api/trainings/delete/{id}")
    public void delete(@PathVariable Long id) {
        trainingService.delete(id);
    }

    @PostMapping("/api/trainings/{trainingid}/adduser/{userid}")
    public void addUser(@PathVariable Long trainingid, @PathVariable Long userid) {
        trainingService.addUser(trainingid, userid);
    }

    @PostMapping("/api/trainings/{trainingid}/removeuser/{userid}")
    public void removeUser(@PathVariable Long trainingid, @PathVariable Long userid) {
        trainingService.removeUser(trainingid, userid);
    }
    @PostMapping("/api/trainings/updateusers")
    public Training updateUsers(@RequestBody Training training) {
        return trainingService.updateUsers(training);
    }

    @PostMapping("/api/trainings/{trainingid}/addday")
    public TrainingDay addDay(@PathVariable Long trainingid, @RequestBody TrainingDay trainingDay) {
        return trainingService.addDay(trainingid, trainingDay);
    }

    @PostMapping("/api/trainings/updateday")
    public void updateDay(@RequestBody TrainingDay trainingDay) {
        trainingService.updateDay(trainingDay);
    }

    @PostMapping("/api/trainings/copyday/{dayid}")
    public TrainingDay copyDay(@PathVariable Long dayid, @RequestBody TrainingDay trainingDay) {
        return trainingService.copyDay(dayid, trainingDay);
    }

    @PostMapping("/api/trainings/removeday/{dayid}")
    public void removeDay(@PathVariable Long dayid) {
        trainingService.removeDay(dayid);
    }

    @GetMapping(value = "/api/trainingday/content/{trainingDayId}", produces = MediaType.TEXT_PLAIN_VALUE )
    public String getContent(@PathVariable Long trainingDayId) {
        return trainingService.getContent(trainingDayId);
    }

    @GetMapping(value = "/api/trainings/content/{trainingId}/{day}" )
    public DayContent getDayContent(@PathVariable Long trainingId, @PathVariable String day) {
        return trainingService.getDayContent(trainingId, day);
    }
    @PostMapping("/api/trainingday/content/{trainingDayId}")
    public void setContent(@PathVariable Long trainingDayId, @RequestBody String content) {
        trainingService.setContent(trainingDayId, content);
    }

}
