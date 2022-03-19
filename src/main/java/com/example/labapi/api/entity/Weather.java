package com.example.labapi.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String city;
    private String unit;
    private Double temperature;

    private static String wrap(String a) {
        return "\"" + a + "\"";
    }

    @Override
    public String toString() {
        return "{" +
                "\"city\":" + wrap(getCity()) + ",\n" +
                "\"unit\":" + wrap(getUnit()) + ",\n" +
                "\"temperature\":" + getTemperature() +
                "}";
    }
}
