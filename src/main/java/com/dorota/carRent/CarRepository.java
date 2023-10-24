package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Car> getAll() {
       return jdbcTemplate.query( "SELECT id, mark, model FROM car",
               BeanPropertyRowMapper.newInstance(Car.class) );
    }

    public int addCar(Car car) {
        jdbcTemplate.update( "INSERT INTO car (mark,model) VALUES(?,?)",car.getMark(),car.getModel());
        return 1;
    }
}
