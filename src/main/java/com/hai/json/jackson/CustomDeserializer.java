package com.hai.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.hai.model.Person;

import java.io.IOException;

public class CustomDeserializer extends StdDeserializer<Person> {

    protected CustomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Person deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Person person = new Person();
        int age = (Integer) ((IntNode) node.get("age")).numberValue();
        String name = node.get("name").asText();
        person.setAge(age);
        person.setName(name);
        return person;
    }
}