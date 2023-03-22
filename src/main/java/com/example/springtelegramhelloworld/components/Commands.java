package com.example.springtelegramhelloworld.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public enum Commands {
    START("/start", "Start", "Start bot"),
    HELP("/help", "Help", "Bot info"),
    WEATHER("/weather", "Weather", "get minsk weather");

    private String incomeCommand;
    private String textOnButton;
    private String descriptionForHelp;

    Commands(String incomeCommand, String textOnButton, String descriptionForHelp) {
        this.incomeCommand = incomeCommand;
        this.textOnButton = textOnButton;
        this.descriptionForHelp = descriptionForHelp;
    }

    /**
     * @return incomeCommand (CallBackQueryText)
     */
    public String getIncomeCommand() {
        return incomeCommand;
    }

    /**
     * @return textOnButton
     */
    public String getText() {
        return textOnButton;
    }

    /**
     * @return descriptionForHelp
     */
    public String getDescription() {
        return descriptionForHelp;
    }


}
