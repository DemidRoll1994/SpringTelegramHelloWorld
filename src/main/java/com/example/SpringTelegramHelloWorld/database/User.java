package com.example.SpringTelegramHelloWorld.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tg_data") //привязываемся к существующей таблице с готовыми колонками
public class User {

    @Id
    private long id; //BigInt
    private String name; //Text
    private int msg_numb; //Integer
}