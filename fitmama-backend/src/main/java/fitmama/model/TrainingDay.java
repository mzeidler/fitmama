package fitmama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String day;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @JsonIgnore
    @Lob
    private String content;
}
