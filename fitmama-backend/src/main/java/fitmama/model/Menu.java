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
public class Menu implements HasIdAndName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuDay> menuDays;

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "menu_user",joinColumns = @JoinColumn(name = "menu_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
