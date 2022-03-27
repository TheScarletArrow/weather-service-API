package com.example.labapi.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class WeatherForecast {
    String city;
    Double temperature;
    @JsonIgnore
    Integer dt;
    String units;
}
