package com.example.actuator_test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.extra.YearWeek;

import java.io.IOException;

@RestController
public class A {
    record Foo1(YearWeek yearWeek) {
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(YearWeek.class, new YearWeekDeserializer());
        objectMapper.registerModule(module);
        final Foo1 foo = objectMapper.readValue("{\"yearWeek\": \"2011-W01\"}", Foo1.class);
        System.out.println();
    }
}

class YearWeekDeserializer extends StdDeserializer<YearWeek> {

    public YearWeekDeserializer() {
        this(null);
    }

    public YearWeekDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    @JsonCreator
    public YearWeek deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final String s = jp.readValueAs(String.class);
        return YearWeek.parse(s);
    }
}