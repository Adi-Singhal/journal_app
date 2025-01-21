package com.example.journalApp.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
            private String observationTime;
            private int temperature;
        @JsonProperty("observation_code")
            private int weatherCode;
        @JsonProperty("weather_icons")
        public ArrayList<String> weatherIcons;
        @JsonProperty("weather_descriptions")
        public ArrayList<String> weatherDescriptions;
    }
}
