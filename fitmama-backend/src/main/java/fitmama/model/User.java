package fitmama.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    
    private String firstName;
    
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String city;

    private String zipcode;

    private String mobile;

    private String email;

    private Date birthDate;

    private BigDecimal height;

}
