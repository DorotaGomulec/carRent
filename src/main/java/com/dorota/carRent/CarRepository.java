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
        return jdbcTemplate.query( "SELECT car.id, car.mark, car.model, class.cartype " +
                        "FROM car " +
                        "LEFT JOIN class " +
                        "ON class.id = car.cartype_id",
                        BeanPropertyRowMapper.newInstance( Car.class ) );
    }

    public int addCar(Car car) {
        int cartype_id = car.getCartype().ordinal()+1;
        jdbcTemplate.update( "INSERT INTO car (mark,model,cartype_id) VALUES(?,?,?)", car.getMark(), car.getModel(), cartype_id);
        return 1;
    }

    public Car getById(int id) {
        return jdbcTemplate.queryForObject( "SELECT car.id, car.mark, car.model, class.cartype FROM car" +
                        " LEFT JOIN class ON car.cartype_id=class.id" +
                        " WHERE car.id=?",
                BeanPropertyRowMapper.newInstance( Car.class ), id );
    }

    public int delete(int id) {
        return jdbcTemplate.update( "DELETE FROM car WHERE id=?", id );
    }

    public int update(Car car) {
        int cartype_id = car.getCartype().ordinal()+1;
        return jdbcTemplate.update( "UPDATE car SET mark=?, model=?, cartype_id=? WHERE id=?",
                car.getMark(), car.getModel(), cartype_id, car.getId() );
    }


}
