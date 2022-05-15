package com.example.labapi.api.service;

import com.example.labapi.api.entity.Weather;

public interface WeatherService {
    boolean save(Weather weather);
    Weather get(String hash);
}
