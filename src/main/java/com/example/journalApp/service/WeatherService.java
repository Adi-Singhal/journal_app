package com.example.journalApp.service;

import com.example.journalApp.api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather_api_key}")
    private String apikey;
    private static final String api="https://api.weatherstack.com/current?access_key=APIKEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city)
    {
        String finalApi=api.replace("APIKEY",apikey).replace("CITY",city);
        ResponseEntity<WeatherResponse> response =restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;
    }


}
