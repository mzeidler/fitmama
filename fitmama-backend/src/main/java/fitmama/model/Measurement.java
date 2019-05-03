package fitmama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    private BigDecimal value1; //opseg grudi

    private BigDecimal value2; //opseg struka

    private BigDecimal value3; //opseg bokova

    private BigDecimal value4; //opseg bedara

    private BigDecimal value5; //opseg desnog bedra

    private BigDecimal value6; //opseg desnog lista

    private BigDecimal value7; //opseg desne nadlaktice

    private BigDecimal value8; //broj kilograma

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
