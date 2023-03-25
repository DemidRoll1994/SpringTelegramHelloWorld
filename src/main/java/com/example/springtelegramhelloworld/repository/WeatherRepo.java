package com.example.springtelegramhelloworld.repository;

import com.example.springtelegramhelloworld.service.CurrentWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherRepo {

    public CurrentWeather getWeather() {
        final RestTemplate restTemplate = new RestTemplate();
/*
        final String stringPosts = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", String.class);
        System.out.println(stringPosts);
        //final CurrentWeather currentWeather = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", CurrentWeather.class);
        /*System.out.println(currentWeather.getCountry());
        System.out.println(currentWeather.getName());*/

        // Map it to list of objects
        /*final CurrentWeather[] currentWeathers = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=minsk&aqi=no", CurrentWeather[].class);
        for (final CurrentWeather post : currentWeathers) {
            System.out.println(post);
        }*/

        final CurrentWeather currentWeather = restTemplate.postForObject("http://api.weatherapi.com/v1/current.json?key=49f9135833b8489f91f93414231802&q=uzda&aqi=no", new CurrentWeather(), CurrentWeather.class);
        if (currentWeather == null) throw new AssertionError();
        log.info(currentWeather.getLocation().getName());
        log.info(currentWeather.getLocation().getCountry());
        log.info(currentWeather.toString());
        return currentWeather;
    }
}
