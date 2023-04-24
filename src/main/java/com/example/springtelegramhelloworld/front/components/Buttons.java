package com.example.springtelegramhelloworld.front.components;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.springtelegramhelloworld.front.components.Command.*;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton(START.getText());
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton(Command.HELP.getText());
    private static final InlineKeyboardButton WEATHER_BUTTON = new InlineKeyboardButton(Command.WEATHER.getText());
    private static final InlineKeyboardButton LANGUAGE_BUTTON = new InlineKeyboardButton(Command.LANGUAGE.getText());
    private static final InlineKeyboardButton RU_LANGUAGE_BUTTON = new InlineKeyboardButton(Command.CHANGE_LANGUAGE_TO_RU.getText());
    private static final InlineKeyboardButton EN_LANGUAGE_BUTTON = new InlineKeyboardButton(Command.CHANGE_LANGUAGE_TO_EN.getText());

    static {
        START_BUTTON.setCallbackData(START.getIncomeCommand());
        HELP_BUTTON.setCallbackData(Command.HELP.getIncomeCommand());
        WEATHER_BUTTON.setCallbackData(Command.WEATHER.getIncomeCommand());
        LANGUAGE_BUTTON.setCallbackData(Command.LANGUAGE.getIncomeCommand());
        RU_LANGUAGE_BUTTON.setCallbackData(Command.CHANGE_LANGUAGE_TO_RU.getIncomeCommand());
        EN_LANGUAGE_BUTTON.setCallbackData(Command.CHANGE_LANGUAGE_TO_EN.getIncomeCommand());
    }

    public static InlineKeyboardMarkup mainMenuButtons(Language language) {
        List<InlineKeyboardButton> rowInline = List.of(getButton(START, language), getButton(HELP, language));
        List<InlineKeyboardButton> rowInline2 = List.of(getButton(LANGUAGE, language), getButton(WEATHER, language));
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline, rowInline2);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }


    public static InlineKeyboardMarkup selectLanguageButtons(Language language) {

        List<InlineKeyboardButton> rowInline = List.of(
                getButton(CHANGE_LANGUAGE_TO_RU, language), getButton(CHANGE_LANGUAGE_TO_EN, language));
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    private static InlineKeyboardButton getButton(Command command, Language language) {
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(language.getValue()));
        InlineKeyboardButton button = new InlineKeyboardButton(bundle.getString(command.getText()));
        button.setCallbackData(command.getIncomeCommand());
        return button;
    }
}