package com.example.springtelegramhelloworld.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
public class CurrentWeather {

    private Location location;
    private Current current;

    public CurrentWeather() {
    }

    public CurrentWeather(Location location, Current current) {
        /*location.setName(nam//*
        location.setCountry(name);
        country = country;*/
        this.location = location;
        this.current= current;
    }
/*
    String name;


    String country;
    /*
    Date localtime;

    double  temp_c;*/



    @Builder
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    static
    public class Location {
        public Location() {
        }

        public Location(String name, String region, String country,
                        double latitude, double longitude, Date localtime) {
            this.name = name;
            this.region = region;
            this.country = country;
            this.lat = latitude;
            this.lon = longitude;
            this.localtime = localtime;
        }

        String name;
        String region;
        String country;
        double lat;
        double lon;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        Date localtime;
    }


    @Builder
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static
    public class Current {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        Date last_updated;
        double temp_c;
        boolean is_day;
        Condition condition;
        double wind_kph;
        //Wind direction in degrees
        int wind_degree;
        String wind_dir;
        double pressure_mb;
        double precip_mm;
        int humidity;
        int cloud;
        double feelslike_c;
        double vis_km;
        double uv;
        double gust_kph;

        public Current(){

        }

        public Current(Date last_updated, double temp_c, boolean is_day
                , Condition condition, double wind_kph, int wind_degree
                , String wind_dir, double pressure_mb, double precip_mm
                , int humidity, int cloud, double feelslike_c, double vis_km
                , double uv, double gust_kph) {
            this.last_updated = last_updated;
            this.temp_c = temp_c;
            this.is_day = is_day;
            this.condition = condition;
            this.wind_kph = wind_kph;
            this.wind_degree = wind_degree;
            this.wind_dir = wind_dir;
            this.pressure_mb = pressure_mb;
            this.precip_mm = precip_mm;
            this.humidity = humidity;
            this.cloud = cloud;
            this.feelslike_c = feelslike_c;
            this.vis_km = vis_km;
            this.uv = uv;
            this.gust_kph = gust_kph;
        }
    }

    @Builder
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Condition {
        String test;

        public Condition() {
        }

        public Condition(String test, String icon, int code) {
            this.test = test;
            this.icon = icon;
            this.code = code;
        }

        //icon_URL
        String icon;
        int code;
    }
}

