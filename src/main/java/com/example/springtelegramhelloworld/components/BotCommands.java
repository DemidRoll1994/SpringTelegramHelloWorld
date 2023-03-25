package com.example.springtelegramhelloworld.components;

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

     String HELP_TEXT = ResourceBundle.getBundle("language\\messages", Locale.forLanguageTag("RU")).getString("help.text");
     /*String HELP_TEXT =
            "This bot is under construction. In future it will provide " +
                    "The following commands are available to you:\n\n" +
                    "/start - start the bot\n" +
                    "/help - help menu";*/

}
