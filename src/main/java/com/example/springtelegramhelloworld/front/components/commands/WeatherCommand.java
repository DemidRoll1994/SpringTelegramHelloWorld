package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.back.repository.WeatherRepo;
import com.example.springtelegramhelloworld.front.view.WeatherView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class WeatherCommand extends Command {

    @Autowired
    private WeatherView weatherView;
    @Autowired
    private WeatherRepo weatherRepo;
    public WeatherCommand() {
        super("/weather", "Weather.textOnButton", "Weather.descriptionForHelp");
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        return weatherView.prepareAnswer(user, weatherRepo.getWeather("Minsk"));
    }
}
