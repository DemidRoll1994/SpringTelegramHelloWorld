package com.example.springtelegramhelloworld.components.answer;

import com.example.springtelegramhelloworld.components.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartAnswer implements Answer {

    @Override
    public SendMessage prepareMessage(Message request ) {


        SendMessage response = new SendMessage();
        request.getChat().setDescription("ru");



        if (request != null) {

        response.setChatId(request.getChatId());
        response.setText( request.getFrom().getFirstName());

        }






        return response;
    }
}
