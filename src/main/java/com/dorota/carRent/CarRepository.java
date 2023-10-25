package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class CarRepository {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> getAll() {
       return jdbcTemplate.query( "SELECT id, mark, model FROM car",
               BeanPropertyRowMapper.newInstance(Car.class) );
    }

    public int addCar(Car car) {
        jdbcTemplate.update( "INSERT INTO car (mark,model) VALUES(?,?)",car.getMark(),car.getModel());
        return 1;
    }

    public Car getById(int id){
        return jdbcTemplate.queryForObject( "SELECT id, mark, model FROM car WHERE id=?",
                BeanPropertyRowMapper.newInstance( Car.class ), id );
    }

    public int delete(int id){
        return jdbcTemplate.update( "DELETE FROM car WHERE id=?",id );
    }

    public void update(Car car) {
        jdbcTemplate.update( "UPDATE car SET mark=?, model=? WHERE id=?",
                car.getMark(), car.getModel(), car.getId());
    }


}
