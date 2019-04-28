package fitmama.jpa;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fitmama.model.Role;

import java.io.IOException;
import java.util.List;

public class RoleSerializer extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<Role> list = (List<Role>)value;
        gen.writeStartArray();
        for (Role obj : list) {
            gen.writeStartObject();
            gen.writeNumberField("id", obj.getId());
            gen.writeStringField("role", obj.getRoleKey().name());
            gen.writeStringField("name", obj.getName());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }

}
