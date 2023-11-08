package com.dorota.carRent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private int id;
    private String mark;
    private String model;
    private CarType cartype;

}
