package fitmama.service;

import fitmama.model.*;
import fitmama.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingDayRepository trainingDayRepository;

    public List<Training> findAll() {
        return trainingRepository.findAllByOrderByIdAsc();
    }

    public Training add(Training training) {
        return trainingRepository.saveAndFlush(training);
    }

    public Training update(Training training) {
        Training trainingDB = getTraining(training.getId());
        if (trainingDB != null) {
            trainingDB.setName(training.getName());
            return trainingRepository.saveAndFlush(trainingDB);
        }
        return training;
    }

    public Training updateUsers(Training training) {
        Training trainingDB = getTraining(training.getId());
        if (trainingDB != null) {
            training.getUsers().forEach(user -> {
                if (!trainingDB.getUsers().contains(user)) {
                    User userDB = getUser(user.getId());
                    if (userDB != null) {
                        trainingDB.getUsers().add(userDB);
                    }
                }
            });
            trainingDB.getUsers().retainAll(training.getUsers());
            return trainingRepository.saveAndFlush(trainingDB);
        }
        return null;
    }

    public void delete(Long id) {
        Training training = getTraining(id);
        if (training != null) {
            training.getUsers().clear();
            trainingRepository.deleteById(id);
        }
    }

    public TrainingDay addDay(Long trainingid, TrainingDay trainingDay) {
        Training training = getTraining(trainingid);
        if (training != null) {

            TrainingDay dayFound = null;
            for (TrainingDay day : training.getTrainingDays()) {
                if (day.getDay().equals(trainingDay.getDay())) {
                    dayFound = day;
                    break;
                }
            }

            if (dayFound != null) {
                dayFound.setName(trainingDay.getName());
                trainingDayRepository.saveAndFlush(dayFound);
            } else {
                trainingDay.setId(null);
                trainingDay.setTraining(training);
                trainingDayRepository.saveAndFlush(trainingDay);
            }
        }

        return trainingDay;
    }

    public void updateDay(TrainingDay trainingDay) {
        TrainingDay day = getTrainingDay(trainingDay.getId());
        if (day != null) {
            day.setName(trainingDay.getName());
            trainingDayRepository.saveAndFlush(day);
        }
    }

    public void removeDay(Long dayid) {
        trainingDayRepository.deleteById(dayid);
    }

    public TrainingDay copyDay(Long dayid, TrainingDay trainingDay) {
        TrainingDay daySource = getTrainingDay(dayid);
        if (daySource != null) {
            trainingDay.setContent(daySource.getContent());
            trainingDay.setName(daySource.getName());
            trainingDay.setTraining(daySource.getTraining());
            trainingDayRepository.saveAndFlush(trainingDay);
        }
        return trainingDay;
    }

    public void addUser(Long trainingid, Long userid) {
        Training training = getTraining(trainingid);
        User user = getUser(userid);

        if (!exists(training, user)) {
            training.getUsers().add(user);
            trainingRepository.saveAndFlush(training);
        }
    }

    public void removeUser(Long trainingid, Long userid) {
        Training training = getTraining(trainingid);
        User user = getUser(userid);

        if (exists(training, user)) {
            training.getUsers().remove(user);
            trainingRepository.saveAndFlush(training);
        }
    }

    private boolean exists(Training training, User user) {
        if (training != null && user != null) {
            for (User trainingUser : training.getUsers()) {
                if (trainingUser.getId().equals(user.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getContent(Long trainingDayId) {
        TrainingDay day = getTrainingDay(trainingDayId);
        return day != null ? day.getContent() : null;
    }

    public void setContent(Long trainingDayId, String content) {
        TrainingDay day = getTrainingDay(trainingDayId);
        if (day != null) {
            day.setContent(content);
            trainingDayRepository.saveAndFlush(day);
        }
    }

    public Training getTraining(Long id) {
        Optional<Training> trainingOpt = trainingRepository.findById(id);
        return trainingOpt.isPresent() ? trainingOpt.get() : null;
    }

    public TrainingDay getTrainingDay(Long id) {
        Optional<TrainingDay> trainingDayOpt = trainingDayRepository.findById(id);
        return trainingDayOpt.isPresent() ? trainingDayOpt.get() : null;
    }

    public User getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.isPresent() ? userOpt.get() : null;
    }
}
