package com.example.springtelegramhelloworld.front.components;

import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/weather", "get minsk weather"),
            new BotCommand("/changeLanguage", "Change Language")
    );

    ResourceBundle bundle = ResourceBundle.getBundle("language\\messages", Locale.forLanguageTag("RU"));
    String HELP_TEXT = bundle.getString("help.text");


}
