package com.cognizant.spring_learn;

import com.cognizant.spring_learn.entity.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    @Bean
    public Country country() {
        Country country = new Country();
        country.setCode("IN");
        country.setName("India");
        return country;
    }

    @Bean
    @Scope("prototype")
    public Country prototypeCountry() {
        Country country = new Country();
        country.setCode("US");
        country.setName("United States");
        return country;
    }

    @Bean
    public List<Country> countryList() {
        List<Country> list = new ArrayList<>();

        Country in = new Country(); in.setCode("IN"); in.setName("India");
        Country us = new Country(); us.setCode("US"); us.setName("United States");
        Country de = new Country(); de.setCode("DE"); de.setName("Germany");
        Country jp = new Country(); jp.setCode("JP"); jp.setName("Japan");

        list.add(in); list.add(us); list.add(de); list.add(jp);
        return list;
    }
}