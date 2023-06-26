package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("/help", "Help.textOnButton", "Help.descriptionForHelp");
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setText(bundle.getString("help.text"));
        return message;
    }
}