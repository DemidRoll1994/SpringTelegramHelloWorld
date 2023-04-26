package com.example.springtelegramhelloworld.front.components;

public class CommandFactory {

    public void executeCommand(Command command){
        switch (command){
            case HELP -> helpMessage();
            case START -> startMessage();
            case WEATHER -> weatherMessage();
            case LANGUAGE -> changeLanguage();
        }
    }

    private void weatherMessage() {

    }

    private void changeLanguage() {

    }

    private void startMessage() {

    }

    private void helpMessage() {

    }


}
