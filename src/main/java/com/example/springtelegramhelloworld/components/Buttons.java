package com.example.springtelegramhelloworld.components;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;

import static com.example.springtelegramhelloworld.components.Commands.*;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton(START.getText());
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton(HELP.getText());
    private static final InlineKeyboardButton WEATHER_BUTTON = new InlineKeyboardButton(WEATHER.getText());
    static {
        START_BUTTON.setCallbackData(START.getIncomeCommand());
        HELP_BUTTON.setCallbackData(HELP.getIncomeCommand());
        WEATHER_BUTTON.setCallbackData(WEATHER.getIncomeCommand());
    }

    public static InlineKeyboardMarkup mainMenuButtons() {

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON, WEATHER_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}