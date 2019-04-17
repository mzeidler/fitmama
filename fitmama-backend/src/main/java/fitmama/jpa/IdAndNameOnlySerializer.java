package fitmama.jpa;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;

public class IdAndNameOnlySerializer extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<HasIdAndName> list = (List<HasIdAndName>)value;
        gen.writeStartArray();
        for (HasIdAndName obj : list) {
            gen.writeStartObject();
            gen.writeNumberField("id", obj.getId());
            gen.writeStringField("name", obj.getName());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }

}
