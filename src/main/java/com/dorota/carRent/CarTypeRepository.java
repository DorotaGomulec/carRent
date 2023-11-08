package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarTypeRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CarTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CarType[] getOption(){
        return CarType.values();
    }
}
