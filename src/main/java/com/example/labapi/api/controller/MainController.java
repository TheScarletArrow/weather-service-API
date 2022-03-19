package com.example.labapi.api.controller;

import com.example.labapi.api.entity.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequestMapping("/api/v1/current/")
public class MainController {
    @Value("${appid}")
    String token;
    @Value(("${site}"))
    String site;

    @GetMapping("/")
    public ResponseEntity<?> hello(@RequestParam(value = "city") String city) throws IOException, ParseException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(site + city + "&appid=" + token + "&units=metric")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        final String responseAsString = response.body().string();
        Weather weather = new Weather();
        weather.setCity(city);


        int indexOfTemp = responseAsString.indexOf("temp");
        int indexOfTempEnd = responseAsString.indexOf(',', indexOfTemp);
        String substring = responseAsString.substring(indexOfTemp - 1, indexOfTempEnd);
        weather.setTemperature(Double.parseDouble(substring.substring(substring.indexOf(":") + 1)));
        weather.setUnit("celsius");
        return ResponseEntity.ok().body(weather);
    }
}
