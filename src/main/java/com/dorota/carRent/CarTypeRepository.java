package com.dorota.carRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class CarTypeRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CarTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarType> getOption(){
        return Arrays.stream( CarType.values() ).toList();
    }


}
