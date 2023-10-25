package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/cars/all")
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    @GetMapping

    @PostMapping("/cars")
    public int addCar(@RequestBody Car car) {
        return carRepository.addCar( car );
    }

    @PatchMapping ("/cars/{id}")
    public void update(@PathVariable int id, @RequestBody Car updatedCar){
        Car car = carRepository.getById( id );
        if(car != null){
            if(updatedCar.getMark() != null) {
                car.setMark( updatedCar.getMark() );
            }
            if (updatedCar.getModel() !=null){
                car.setModel( updatedCar.getModel() );
            }
        }
        carRepository.update(car );

    }

    @DeleteMapping ("/cars/{id}")
    public int delete(@PathVariable int id){
        return carRepository.delete( id );
    }

}
