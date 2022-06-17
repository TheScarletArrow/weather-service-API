package com.example.labapi.api.service;

import com.example.labapi.api.entity.Weather;
import com.example.labapi.api.repository.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;


    @Override
    public boolean save(Weather weather) {
        return weatherRepo.save(weather);
    }

    @Override
    public Weather get(String hash) {
        return weatherRepo.getWeatherByHash(hash);
    }
}
