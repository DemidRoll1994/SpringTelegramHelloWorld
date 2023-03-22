package com.example.springtelegramhelloworld.components.answer;

import com.example.springtelegramhelloworld.components.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartAnswer implements Answer {

    @Override
    public SendMessage prepareMessage(Message request) {


        SendMessage response = new SendMessage();
/*
        if (message != null) {

        response.setChatId(message.getChatId());
        response.setText("Hi, " + message.getFrom().getFirstName() + "! I'm a Telegram bot.");

            if (update.getMessage().hasText()) {
                receivedMessage = message.getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        }

*/




        return response;
    }
}
