package com.example.actuator_test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.threeten.extra.YearWeek;

import java.awt.*;

public class C {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.addMixIn(YearWeek.class, YearWeekMixIn.class);
//        objectMapper.readValue("{\"yearWeek\": \"2011-W01\"}", Foo.class);
        System.out.println();
    }
}



abstract class YearWeekMixIn {

    @JsonCreator
    public static YearWeek parse(String value) {
        return YearWeek.parse(value);
    }
}

record Foo(YearWeek yearWeek) {
}