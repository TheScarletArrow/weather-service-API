package com.example.labapi.api.controller;

import com.example.labapi.api.entity.Forecast;
import com.example.labapi.api.entity.Hourly;
import com.example.labapi.api.entity.WeatherForecast;
import com.example.labapi.api.exceptions.NoArgumentsException;
import com.example.labapi.api.exceptions.NoSuchCityException;
import com.example.labapi.api.exceptions.WrongDTException;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/forecast/")
public class ForecastController {
    @Value("${appid}")
    String token;

    @Value("${cityToGeo}")
    String cityToGeo;
    @Value("${forecast}")
    String forecastInCity;

    @GetMapping("/")
    public ResponseEntity<?> forecast(
            @RequestParam String city,
            @RequestParam Integer dt,
            @RequestParam(required = false) String units) {
        if (StringUtils.isBlank(city)) {
            throw new NoArgumentsException("City not set");
        }
        if (StringUtils.isBlank(units) || units == null|| !StringUtils.equals(city, "imperial")) {
            units = "metric";
        }

        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url(cityToGeo + city + "&appid=" + token)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() == 404) throw new NoSuchCityException();
            if (response.code() == 200) {
                String findLongAndLat = Objects.requireNonNull(response.body()).string();
                int indexOfLat = findLongAndLat.indexOf("lat");
                int indexOfLatEnd = findLongAndLat.indexOf(',', indexOfLat);

                int indexOfLon = findLongAndLat.indexOf("lon");
                int indexOfLonEnd = findLongAndLat.indexOf(',', indexOfLon);
                String[] lat = findLongAndLat.substring(indexOfLat, indexOfLatEnd).replaceAll("\"", "").split(":");
                String[] lon = findLongAndLat.substring(indexOfLon, indexOfLonEnd).replaceAll("\"", "").split(":");

                double latitude = Double.parseDouble(lat[1]);
                double longitude = Double.parseDouble(lon[1]);

                OkHttpClient client1 = new OkHttpClient().newBuilder().build();
                Request request1 = new Request.Builder()
                        .url(forecastInCity + "lat=" + latitude + "&lon=" + longitude + "&appid=" + token + "&units=" + units)
                        .method("GET", null)
                        .build();
                Response response1 = client1.newCall(request1).execute();

                ResponseBody response1body = response1.body();

                assert response1body != null;
                final String string = response1body.string();
                int indexOfHourly = string.indexOf("hourly");
                int indexOfHourlyEnd = string.indexOf("daily");
                final String substring = string.substring(indexOfHourly - 1, indexOfHourlyEnd - 2);
                String json = "{" + substring + "}";
                Hourly hourly = new Gson().fromJson(json, Hourly.class);
                if (dt == null || dt == 0) {
                    return ResponseEntity.ok().body(hourly);
                }
                Optional<Forecast> forecast = hourly.getHourly().stream().filter(p -> p.getDt().equals(dt)).findFirst();

                if (forecast.isPresent()) {
                    WeatherForecast weatherForecast = new WeatherForecast();
                    weatherForecast.setDt(forecast.get().getDt());
                    weatherForecast.setCity(city);
                    weatherForecast.setTemperature(forecast.get().getTemp());
                    weatherForecast.setUnits(units);
                    return ResponseEntity.ok(weatherForecast);
                } else
                    throw new WrongDTException();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
