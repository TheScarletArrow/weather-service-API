package com.example.labapi.api.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("weather")
public class Weather implements Serializable {
    @Id
    private String city;
    private String units;
    private Double temperature;

    private static String wrap(String a) {
        return "\"" + a + "\"";
    }

    @Override
    public String toString() {
        return "{" +
                "\"city\":" + wrap(getCity()) + ",\n" +
                "\"unit\":" + wrap(getUnits()) + ",\n" +
                "\"temperature\":" + getTemperature() +
                "}";
    }
}
