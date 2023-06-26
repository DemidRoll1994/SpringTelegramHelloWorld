package com.example.springtelegramhelloworld.front.view;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.front.bot.Buttons222;
import com.example.springtelegramhelloworld.back.service.CurrentWeather;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class WeatherView {

    public SendMessage prepareAnswer(User user, CurrentWeather currentWeather){
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setText(String.format(
                bundle.getString("weather.text")
                , currentWeather.getLocation().getName()
                , currentWeather.getLocation().getCountry()
                , currentWeather.getCurrent().getTemp_c()
                , currentWeather.getCurrent().getWind_dir()
                , currentWeather.getCurrent().getWind_kph()
                , currentWeather.getCurrent().getPressure_mb()
                , currentWeather.getCurrent().getPrecip_mm()
                , currentWeather.getCurrent().getHumidity()
                , currentWeather.getCurrent().getCloud()));
        message.setReplyMarkup(Buttons222.mainMenuButtons(user.getLanguage()));


        return message;
    }

}
