package com.example.springtelegramhelloworld.front.bot;


import com.example.springtelegramhelloworld.front.components.Command222;
import com.example.springtelegramhelloworld.front.components.Language;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.springtelegramhelloworld.front.components.Command222.*;

@Slf4j
public class Buttons222 {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton(START.getText());
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton(Command222.HELP.getText());
    private static final InlineKeyboardButton WEATHER_BUTTON = new InlineKeyboardButton(Command222.WEATHER.getText());
    private static final InlineKeyboardButton LANGUAGE_BUTTON = new InlineKeyboardButton(Command222.LANGUAGE.getText());
    private static final InlineKeyboardButton RU_LANGUAGE_BUTTON = new InlineKeyboardButton(Command222.CHANGE_LANGUAGE_TO_RU.getText());
    private static final InlineKeyboardButton EN_LANGUAGE_BUTTON = new InlineKeyboardButton(Command222.CHANGE_LANGUAGE_TO_EN.getText());

    static {
        START_BUTTON.setCallbackData(START.getIncomeCommand());
        HELP_BUTTON.setCallbackData(Command222.HELP.getIncomeCommand());
        WEATHER_BUTTON.setCallbackData(Command222.WEATHER.getIncomeCommand());
        LANGUAGE_BUTTON.setCallbackData(Command222.LANGUAGE.getIncomeCommand());
        RU_LANGUAGE_BUTTON.setCallbackData(Command222.CHANGE_LANGUAGE_TO_RU.getIncomeCommand());
        EN_LANGUAGE_BUTTON.setCallbackData(Command222.CHANGE_LANGUAGE_TO_EN.getIncomeCommand());
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

    private static InlineKeyboardButton getButton(Command222 command, Language language) {
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(language.getValue()));
        log.info("generate button with lang: "+ language.getValue());
        InlineKeyboardButton button = new InlineKeyboardButton(bundle.getString(command.getText()));

        log.info("generate button with text: "+ bundle.getString(command.getText()));
        button.setCallbackData(command.getIncomeCommand());
        return button;
    }
}