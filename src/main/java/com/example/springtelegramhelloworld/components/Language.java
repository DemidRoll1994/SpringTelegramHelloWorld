package com.example.springtelegramhelloworld.components;

import lombok.Getter;

@Getter
public enum Language {
    RU("RU"),
    EN("EN");

    private String value;

    Language(String value) {
        this.value = value;
    }

}
