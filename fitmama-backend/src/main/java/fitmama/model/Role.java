package fitmama.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fitmama.jpa.HasIdAndName;
import fitmama.jpa.IdAndNameOnlySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements HasIdAndName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleKey roleKey;

    private String name;

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "role_user",joinColumns = @JoinColumn(name = "role_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
