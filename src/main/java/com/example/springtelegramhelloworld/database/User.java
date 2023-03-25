package com.example.springtelegramhelloworld.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/*
@Data
@Entity(name = "tg_data") //привязываемся к существующей таблице с готовыми колонками
public class User {

    @Id
    private long id; //BigInt
    private String name; //Text
    private int msg_numb; //Integer
}*/

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long userId;
    private String language;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_weatherCity",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "weatherCity_id") }
    )
    Set<WeatherCity> weatherCities = new HashSet<>();


}

