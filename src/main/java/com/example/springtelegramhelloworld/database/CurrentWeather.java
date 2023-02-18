package com.example.springtelegramhelloworld.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    String name;
    String country;
    /*
    Date localtime;

    double  temp_c;*/

    public String toString() {
        return String.format("\n id: %s \n title: %s \n body: %s \n userId: %d \n", name, country,"",-9999 /*localtime.toString(), temp_c*/);
    }
}
