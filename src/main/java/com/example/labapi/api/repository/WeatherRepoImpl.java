package com.example.labapi.api.repository;

import com.example.labapi.api.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepoImpl implements WeatherRepo {

    private final static String KEY = "weather";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean save(Weather weather) {
        try {
            redisTemplate.opsForHash().put(KEY + "_" + weather.getCity(), weather.getCity(), weather);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Weather getWeatherByHash(String hash) {
        Weather weather;
        weather = (Weather) redisTemplate.opsForHash().get(hash, hash.substring(KEY.length()+1));
        return weather;
    }

//    @Override
//    public Optional<Weather> getWeatherByHash(String hash) {
//        try {
//
//            return (Optional<Weather>) redisTemplate.opsForHash().get(hash, hash.substring(0, hash.indexOf('_')));
//        } catch (Exception e) {
//
//        }
//    }
}
