package com.example.springtelegramhelloworld.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "weather_city")
@Data
@AllArgsConstructor
public class WeatherCity {

    @Id
    @GeneratedValue
    private Long cityId;
    private String city;
    private String country;
    @ManyToMany(mappedBy = "weatherCities")
    private Set<User> users = new HashSet<>();
}
