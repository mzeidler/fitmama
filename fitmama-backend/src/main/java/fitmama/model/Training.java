package fitmama.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fitmama.jpa.IdAndNameOnlySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<TrainingDay> trainingDays;

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "training_user",joinColumns = @JoinColumn(name = "training_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
