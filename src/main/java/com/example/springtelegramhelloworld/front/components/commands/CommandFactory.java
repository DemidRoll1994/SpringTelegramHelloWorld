package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class CommandFactory {


    public static Command parseCommand(String receivedMessage) {
        log.info("receivedMessage is:" + receivedMessage);
        Command command = new StartCommand();
        Command.class.getClasses();
        switch (receivedMessage) {
            case "/start"              -> command = new StartCommand();
            case "/help"               -> command = new HelpCommand();
            case "/weather"            -> command = new WeatherCommand();
            case "/changeLanguage"     -> command = new LanguageCommand();
            case "/changeLanguageToRU" -> command = new ChangeLangToRuCommand();
            case "/changeLanguageToEN" -> command = new ChangeLangToEnCommand();
        }
        return command;
    }


    public void executeCommand(Command command, User user, Update update){
            command.prepareMessage(user, update);
            log.info("Reply sent");
    }

}
