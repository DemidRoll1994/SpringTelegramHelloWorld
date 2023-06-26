package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.front.components.BundleController;
import com.example.springtelegramhelloworld.front.components.Language;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ResourceBundle;

@Slf4j
public abstract class Command {

    private static String incomeCommand = " ";
    private static String textOnButton = " ";
    private static String descriptionForHelp = " ";

    public Command() {

    }

    protected Command(String incomeCommand, String textOnButton, String descriptionForHelp) {
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


    public InlineKeyboardButton getTelegramButton(Language language) {
        ResourceBundle bundle = BundleController.getBundle(language);

        log.info("generate button with lang: " + language.getValue());
        InlineKeyboardButton button
                = new InlineKeyboardButton(bundle.getString(getText()));

        log.info("generate button with text: " + bundle.getString(getText()));
        button.setCallbackData(getIncomeCommand());
        return button;
    }

    public abstract SendMessage prepareMessage(User user, Update update);

}
