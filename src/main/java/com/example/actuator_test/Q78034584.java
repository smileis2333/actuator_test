package com.example.actuator_test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.threeten.extra.YearWeek;

public class Q78034584 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(YearWeek.class, YearWeekMixIn1.class);

        final Foo33 foo = objectMapper.readValue("{\"yearWeek\": \"2011-W01\"}", Foo33.class);
        System.out.println();
        System.out.println();
    }

    class YearWeekMixIn1 {

        @JsonCreator
        public static YearWeek parse(CharSequence text) {
            return null;
        }
    }

    record Foo33(YearWeek yearWeek) {
    }
}


