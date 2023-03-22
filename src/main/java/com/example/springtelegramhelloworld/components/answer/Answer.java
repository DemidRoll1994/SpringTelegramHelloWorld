package com.example.springtelegramhelloworld.components.answer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Answer {
    public SendMessage prepareMessage(Message message);
}
