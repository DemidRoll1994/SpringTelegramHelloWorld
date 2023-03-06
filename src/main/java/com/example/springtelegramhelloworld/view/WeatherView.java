package com.example.springtelegramhelloworld.view;

import com.example.springtelegramhelloworld.components.Buttons;
import com.example.springtelegramhelloworld.database.CurrentWeather;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class WeatherView {
    private final String WEATHER_FORMAT = "Current weather in %s %s \nTemp: %.1f \nWind: %s %.1f kmh \nPressure: %.1f \nPrecipitation amount %.1fmm \nHumidity %d \nCloud cover %d";/*"current weather in %s, %s,\n Temp: %d" +
            ",\nWind: %d, %dkmh, ,\npressure: %d ,\nPrecipitation amount %dmm,\n" +
            "humidity %d%,\nCloud cover %d%."*/; //todo

    public SendMessage prepareAnswer(long chatId, CurrentWeather currentWeather){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        message.setText(String.format(WEATHER_FORMAT
                , currentWeather.getLocation().getName()
                , currentWeather.getLocation().getCountry()
                , currentWeather.getCurrent().getTemp_c()
                , currentWeather.getCurrent().getWind_dir()
                , currentWeather.getCurrent().getWind_kph()
                , currentWeather.getCurrent().getPressure_mb()
                , currentWeather.getCurrent().getPrecip_mm()
                , currentWeather.getCurrent().getHumidity()
                , currentWeather.getCurrent().getCloud()));
        message.setReplyMarkup(Buttons.inlineMarkup());


        return message;
    }

}
