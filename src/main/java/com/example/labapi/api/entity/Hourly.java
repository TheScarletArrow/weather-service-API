package com.example.labapi.api.entity;

import lombok.*;

import java.util.ArrayList;
/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hourly {
    ArrayList<Forecast> hourly;

}
