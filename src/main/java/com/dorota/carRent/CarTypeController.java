package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/types")
public class CarTypeController {

    private CarTypeRepository carTypeRepository;

    @Autowired
    public CarTypeController(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    @GetMapping("")
    public CollectionModel<CarType> getCarTypes() {
        List<CarType> carTypeList = carTypeRepository.getOption();
        Link linkToEconomyCars = (Link) WebMvcLinkBuilder.linkTo( CarTypeController.class ).slash( 1 ).withRel( "ecomony-cars" );
        Link linkToPremiumCars = (Link) WebMvcLinkBuilder.linkTo( CarTypeController.class ).slash( 2 ).withRel( "premium-cars" );

        return CollectionModel.of( carTypeList, WebMvcLinkBuilder.linkTo( CarTypeController.class ).withSelfRel(),
                linkToEconomyCars, linkToPremiumCars );
    }

}
