package com.example.springtelegramhelloworld.back.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Exercise")
@Data
@AllArgsConstructor
public class Exercise {

    @Id
    private Long exerciseId;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="training_id", nullable=false)
    private Training training;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    public Exercise() {
    }

}
