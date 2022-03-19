package com.example.labapi.api.controller;

import com.example.labapi.api.entity.Weather;
import com.example.labapi.api.exceptions.NoArgumentsException;
import com.example.labapi.api.exceptions.NoSuchCityException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController()
@RequestMapping("/api/v1/current/")
public class MainController {
    @Value("${appid}")
    String token;
    @Value("${site}")
    String site;

    @GetMapping("/")
    public ResponseEntity<?> hello(@RequestParam(value = "city", required = false) String city) throws IOException {
        if(StringUtils.isBlank(city))
        {
            throw new NoArgumentsException("City not set");
        }
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(site + city + "&appid=" + token + "&units=metric")
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
                weather.setTemperature(Double.parseDouble(substring.substring(substring.indexOf(":") + 1)));
                weather.setUnit("celsius");
                return ResponseEntity.ok().body(weather);

            }
        } catch (NoArgumentsException e) {
            return ResponseEntity.badRequest().body("No city set");
        }
        return ResponseEntity.accepted().body("Hmm");
    }
}
