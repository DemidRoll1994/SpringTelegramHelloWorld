package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.front.bot.Buttons222;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageCommand extends Command {

    public LanguageCommand() {
        super("/changeLanguage", "ChangeLanguage.textOnButton","ChangeLanguage.descriptionForHelp");
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        SendMessage message = new SendMessage();
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setChatId(user.getUserId());
        message.setText(bundle.getString("ChangeLanguage.preview"));
        message.setReplyMarkup(Buttons222.selectLanguageButtons(user.getLanguage()));
        return message;
    }
}