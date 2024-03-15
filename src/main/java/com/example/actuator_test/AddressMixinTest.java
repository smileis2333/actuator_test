package com.example.actuator_test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.threeten.extra.YearWeek;

public class AddressMixinTest {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

//        objectMapper.addMixIn(Address.class, AddressMixin.class);
//        final Address deserializedUser = objectMapper.readValue("{\"city\":\"111\",\"state11\":\"222\"}", Address.class);

        objectMapper.addMixIn(YearWeekCustom.class, YearWeekCustomMixin.class);
        final YearWeekCustomWrapper yearWeekCustomWrapper = objectMapper.readValue("{\"yearWeek\":\"2011-W01\"}", YearWeekCustomWrapper.class);
        System.out.println();
    }
}

record YearWeekCustomWrapper(YearWeekCustom yearWeek) {

}

class YearWeekCustom {
    private final int year;
    private final int week;

    public YearWeekCustom(int weekBasedYear, int week) {
        this.year = weekBasedYear;
        this.week = week;
    }

//    @JsonCreator
    public static YearWeekCustom parse(String s) {
        final YearWeek parse = YearWeek.parse(s);
        return new YearWeekCustom(parse.getYear(), parse.getWeek());
    }
}

class YearWeekCustomMixin{
    @JsonCreator
    public static YearWeekCustom parse(String s) {
        final YearWeek parse = YearWeek.parse(s);
        return new YearWeekCustom(parse.getYear(), parse.getWeek());
    }
}


class Address {

    private String city;
    private String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", state=" + state + "]";
    }
}

abstract class AddressMixin {

    @JsonCreator
    public AddressMixin(
            @JsonProperty("city") String city,
            @JsonProperty("state11") String state) {
        System.out.println("Wont be called");

    }
}