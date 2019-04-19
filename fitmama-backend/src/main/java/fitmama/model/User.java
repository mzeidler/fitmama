package fitmama.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fitmama.jpa.HasIdAndName;
import fitmama.jpa.IdAndNameOnlySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements HasIdAndName {

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

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(mappedBy = "users", cascade=CascadeType.PERSIST)
    private List<Menu> menus;

    @Transient
    @Override
    public String getName() {
        return firstName + " " + lastName;
    }
}
