package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarTypeController {

    private CarTypeRepository carTypeRepository;

    @Autowired
    public CarTypeController(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    @GetMapping("/cars/types")
    public CarType[] getCarTypes() {
        return carTypeRepository.getOption();
    }

}
