package com.example.labapi.api.entity;

import lombok.*;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forecast {
    Integer dt;
    Double temp;
    Double feels_like;
    Integer pressure;
    Integer humidity;
    Double dew_point;
    Double uvi;
    Integer clouds;
    Integer visibility;
    Double wind_speed;
    Integer wind_deg;
    Double wind_gust;

    Double pop;
}
