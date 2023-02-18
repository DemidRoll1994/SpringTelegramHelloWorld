package com.example.springtelegramhelloworld.bot;

import com.example.springtelegramhelloworld.database.CurrentWeather;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeatherRepo {

    public void getWeather() {
        final RestTemplate restTemplate = new RestTemplate();

        final String stringPosts = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", String.class);
        System.out.println(stringPosts);
        // Map it to list of objects
        /*final CurrentWeather[] currentWeathers = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", CurrentWeather[].class);
        for (final CurrentWeather post : currentWeathers) {
            System.out.println(post);
        }*/

        final CurrentWeather currentWeather;
            currentWeather = CurrentWeather.builder()
                    .name("name")
                    .country("country")
                    .build();

        final CurrentWeather insertedPost = restTemplate.postForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", currentWeather, CurrentWeather.class);
        System.out.println(insertedPost);
    }
}
