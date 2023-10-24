package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
@Autowired
    private CarRepository carRepository;

    @GetMapping("/test")
        public String test(){
        return "ok";
    }

    @GetMapping("/cars/all")
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    @PostMapping("/cars")
    public int addCar(@RequestBody Car car){
        return carRepository.addCar( car );
    }

}
