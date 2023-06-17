package com.example.springtelegramhelloworld.back.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Training")
@Data
@AllArgsConstructor
public class Training {

        @Id
        private Long trainingId;
        private String name;
        private String description;
        private Calendar dayOfWeek;

        @OneToMany(mappedBy="exercise")
        private Set<Exercise> exercises;


        @ManyToOne
        @JoinColumn(name="user_id", nullable=false)
        private User user;

        public Training(){
        }


    }

