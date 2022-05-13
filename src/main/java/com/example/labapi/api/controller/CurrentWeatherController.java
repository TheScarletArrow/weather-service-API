package com.example.labapi.api.controller;

import com.example.labapi.api.entity.Weather;
import com.example.labapi.api.exceptions.NoArgumentsException;
import com.example.labapi.api.exceptions.NoSuchCityException;
import com.example.labapi.api.service.WeatherService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Anton Yurkov
 * @version 0.0.5
 */

@RestController
@RequestMapping("/api/v1/current/")
public class CurrentWeatherController {
    @Value("${appid}")
    String token;
    @Value("${site}")
    String site;

    @Autowired
    private WeatherService weatherService;

    @PutMapping("/")
    public ResponseEntity<?> hello(@RequestParam(value = "city", required = false) String city,
                                   @RequestParam(value = "units", required = false) String units) throws IOException {
        if (StringUtils.isBlank(city)) {
            throw new NoArgumentsException("City not set");
        }
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            if (StringUtils.isBlank(units)) units = "metric";
            Request request = new Request.Builder()
                    .url(site + city + "&appid=" + token + "&units=" + units)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() == 404) throw new NoSuchCityException("No such city");
            if (response.code() == 200) {
                Weather weather = new Weather();
                weather.setCity(city);
                final String responseAsString = Objects.requireNonNull(response.body()).string();
                int indexOfTemp = responseAsString.indexOf("temp");
                int indexOfTempEnd = responseAsString.indexOf(',', indexOfTemp);
                String substring = responseAsString.substring(indexOfTemp - 1, indexOfTempEnd);
                double temperature = Double.parseDouble(substring.substring(substring.indexOf(":") + 1));
                if (units.equals("metric") || units.equals("imperial")) {
                    weather.setTemperature(temperature);
                    weather.setUnits(units);
                } else {
                    throw new NoArgumentsException();
                }
                weatherService.save(weather);
                return ResponseEntity.ok("");
            }
        } catch (NoArgumentsException e) {
            return ResponseEntity.badRequest().body("Missing one or all the arguments, or they are typed incorrectly or have the wrong value");
        }
        return ResponseEntity.accepted().body("Hmm");
    }

    @GetMapping("/")
    public ResponseEntity<?> get(@RequestParam String city){
        return ResponseEntity.ok(weatherService.get("weather_"+city));
    }
}
