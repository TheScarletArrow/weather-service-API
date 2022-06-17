package com.example.labapi.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
@Data
public class WeatherForecast {
    String city;
    Double temperature;
    @JsonIgnore
    Integer dt;
    String units;
}
