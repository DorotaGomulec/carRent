package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
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

    @GetMapping("")
    public CollectionModel<EntityModel<Car>> getAll() {
        List<EntityModel<Car>> cars = carRepository.getAll().stream().
                map( car -> EntityModel.of( car ) ).toList();

        for (EntityModel<Car> car : cars) {
            int carID = car.getContent().getId();
            Link link = (Link)  WebMvcLinkBuilder.linkTo(CarController.class).slash( carID ).withSelfRel();
            Link deleteLink = (Link) WebMvcLinkBuilder.linkTo( CarController.class ).slash( carID ).withRel( "delete-car" ) ;
            car.add(link,deleteLink);

        }
        return CollectionModel.of( cars,
                WebMvcLinkBuilder.linkTo( CarController.class ).withSelfRel() );
    }

    @GetMapping("/{id}")
    public EntityModel<Car> getByID(@PathVariable int id){
        return EntityModel.of(carRepository.getById( id ),
                WebMvcLinkBuilder.linkTo(CarController.class).slash( id ).withSelfRel(),
                WebMvcLinkBuilder.linkTo(CarController.class).withRel( "all-cars" ),
                WebMvcLinkBuilder.linkTo(CarController.class).slash( id ).withRel( "delete" )
                );

    }

    @PostMapping("")
    public int addCar(@RequestBody Car car) {
        return carRepository.addCar( car );
    }

    @PatchMapping ("/{id}")
    public int update(@PathVariable int id, @RequestBody Car updatedCar){
        Car car = carRepository.getById( id );
        if(car != null){
            if(updatedCar.getMark() != null) {
                car.setMark( updatedCar.getMark() );
            }
            if (updatedCar.getModel() !=null){
                car.setModel( updatedCar.getModel() );
            }
            if (updatedCar.getCartype() !=null){
                car.setCartype( updatedCar.getCartype() );
            }
        }
        return carRepository.update(car );
    }

    @DeleteMapping ("/{id}")
    public int delete(@PathVariable int id){
        return carRepository.delete( id );
    }

}
