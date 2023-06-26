package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.back.database.UserRepository;
import com.example.springtelegramhelloworld.front.bot.Buttons222;
import com.example.springtelegramhelloworld.front.components.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ChangeLangToEnCommand extends Command {
    @Autowired
    private UserRepository userRepository;

    public ChangeLangToEnCommand() {
        super("/changeLanguageToEN", "ChangeLanguageToEn.textOnButton", "ChangeLanguageToEn.descriptionForHelp");
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        Language newlanguage = Language.EN;
        SendMessage message = new SendMessage();
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setChatId(user.getUserId());
        message.setText(String.format(bundle.getString("ChangeLanguage.result"), newlanguage));
        user.setLanguage(newlanguage);
        userRepository.save(user);
        message.setReplyMarkup(Buttons222.mainMenuButtons(user.getLanguage()));
        return message;
    }
}