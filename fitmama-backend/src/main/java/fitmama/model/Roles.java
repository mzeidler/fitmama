package fitmama.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fitmama.jpa.IdAndNameOnlySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @JsonSerialize(using = IdAndNameOnlySerializer.class)
    private List<Role> roles;
}
