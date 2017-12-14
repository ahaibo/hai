package com.hai.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hai.model.Person;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<Person> {

    protected CustomSerializer(Class<Person> t) {
        super(t);
    }

    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("age", person.getAge());
        jsonGenerator.writeStringField("name", person.getName());
        jsonGenerator.writeEndObject();
    }
}