package com.example.labapi.api.repository;

import com.example.labapi.api.entity.Weather;

public interface WeatherRepo {
    boolean save(Weather weather);
    Weather getWeatherByHash(String hash);
}
