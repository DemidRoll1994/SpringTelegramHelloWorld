package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.back.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChangeLangToRuCommand extends Command {
    @Autowired
    private UserRepository userRepository;
    public ChangeLangToRuCommand() {
        super("/changeLanguageToRU", "ChangeLanguageToRu.textOnButton" , "ChangeLanguageToRu.descriptionForHelp");
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        return null;
    }
}