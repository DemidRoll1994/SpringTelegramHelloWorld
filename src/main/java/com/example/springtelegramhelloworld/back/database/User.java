package com.example.springtelegramhelloworld.back.database;

import com.example.springtelegramhelloworld.front.components.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    private Long userId;
    private Language language;



    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_weatherCity",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "weatherCity_id") }
    )
    Set<WeatherCity> weatherCities = new HashSet<>();


    @OneToMany(mappedBy="exercise")
    private Set<Exercise> exercises;

    @OneToMany(mappedBy="training")
    private Set<Training> trainings;

    public User() {

    }


    public User(long userId, Language language) {
        this();
        this.userId=userId;
        this.language=language;
    }
}

