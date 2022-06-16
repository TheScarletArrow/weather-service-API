package com.example.labapi.api.controller;

import com.example.labapi.grpc.GrpcClient;
import com.example.labapi.api.entity.Weather;
import com.example.labapi.api.exceptions.NoArgumentsException;
import com.example.labapi.api.exceptions.NoSuchCityException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/current/")
public class CurrentWeatherController {
    @Value("${appid}")
    String token;
    @Value("${site}")
    String site;

    @GetMapping("/")
    public ResponseEntity<?> hello(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                   // @RequestParam(value = "name") String name,
                                    @RequestParam(value = "city", required = false) String city,
                                   @RequestParam(value = "units", required = false) String units) throws IOException {
        final String OWN_AUTH_USERNAME = "Own-Auth-UserName";
        String name=servletRequest.getHeader(OWN_AUTH_USERNAME);
        final boolean authenticated = GrpcClient.getAuthenticated(name);
        if (!authenticated)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(name+" is not authenticated");

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
                response.close();
                log.info("Put"+LocalDateTime.now());
                return ResponseEntity.ok(weather);
            }
        } catch (NoArgumentsException e) {
            return ResponseEntity.badRequest().body("Missing one or all the arguments, or they are typed incorrectly or have the wrong value");
        }

        return ResponseEntity.accepted().body("Hmm");
    }

//    @GetMapping("/")
//    public ResponseEntity<?> get(@RequestParam String city, HttpServletResponse response)  {
////        if (hello(city, "metrics").getStatusCodeValue()==404) return ResponseEntity.status(404).build();
//        Weather weather = weatherService.get("weather_" + city);
//        if (weather!=null){
//            System.out.println(LocalDateTime.now());
//            return ResponseEntity.ok(weather);
//        }
//        else
//            return ResponseEntity.status(response.getStatus()).build();
//    }
}
