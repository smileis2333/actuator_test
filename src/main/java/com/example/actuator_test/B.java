package com.example.actuator_test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.extra.YearWeek;

import java.io.IOException;

@RestController
public class B {
    record Foo2(@JsonDeserialize(converter = YearWeekConverter.class) YearWeek yearWeek) {
    }


    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(YearWeek.class, new YearWeekDeserializer());
//        objectMapper.registerModule(module);
        final Foo2 foo = objectMapper.readValue("{\"yearWeek\": \"2011-W01\"}", Foo2.class);
        System.out.println();
    }
}

class YearWeekConverter extends StdConverter<String, YearWeek> {
    @Override
    public YearWeek convert(String s) {
        return YearWeek.parse(s);
    }
}
