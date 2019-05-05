package fitmama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fitmama.jpa.HasIdAndName;
import fitmama.jpa.IdAndNameOnlySerializer;
import fitmama.jpa.RoleSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    private String birthDate;

    private BigDecimal height;

    private String password;

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(mappedBy = "users", cascade=CascadeType.PERSIST)
    @OrderBy("name ASC")
    private List<Menu> menus;

    @JsonSerialize(using = RoleSerializer.class)
    @ManyToMany(mappedBy = "users", cascade=CascadeType.PERSIST)
    @OrderBy("name ASC")
    private List<Role> roles;

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(mappedBy = "users", cascade=CascadeType.PERSIST)
    @OrderBy("name ASC")
    private List<Training> trainings;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    @Transient
    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();

        if (firstName != null) {
            sb.append(firstName + " ");
        }

        if (lastName != null) {
            sb.append(lastName);
        }

        if (sb.length() == 0 && username != null) {
            sb.append(username);
        }

        if (sb.length() == 0) {
            sb.append("User id=" + id);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
