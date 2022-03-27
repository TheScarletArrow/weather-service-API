package com.example.labapi.api.entity;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hourly {
    ArrayList<Forecast> hourly;

}
