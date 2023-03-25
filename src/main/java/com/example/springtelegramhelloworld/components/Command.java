package com.example.springtelegramhelloworld.components;

public enum Command {
    START("/start", "Start.textOnButton", "Start.descriptionForHelp"),
    HELP("/help", "Help.textOnButton", "Help.descriptionForHelp"),
    WEATHER("/weather", "Weather.textOnButton", "Weather.descriptionForHelp"),
    LANGUAGE ("/changeLanguage", "ChangeLanguage.textOnButton","ChangeLanguage.descriptionForHelp");


    private String incomeCommand;
    private String textOnButton;
    private String descriptionForHelp;

    Command(String incomeCommand, String textOnButton, String descriptionForHelp) {
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
     * @return ref to textOnButton
     */
    public String getText() {
        return textOnButton;
    }

    /**
     * @return ref to descriptionForHelp
     */
    public String getDescription() {
        return descriptionForHelp;
    }


}
