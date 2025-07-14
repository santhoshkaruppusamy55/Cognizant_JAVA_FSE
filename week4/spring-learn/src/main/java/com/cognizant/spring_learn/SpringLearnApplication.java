package com.cognizant.spring_learn;

import com.cognizant.spring_learn.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.PostConstruct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    @Autowired
    private SimpleDateFormat dateFormat;

    @Autowired
    private Country country;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private List<Country> countryList;

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Spring Boot Application Started");
    }

    @PostConstruct
    public void displayDate() throws ParseException {
        LOGGER.info("START displayDate()");
        Date date = dateFormat.parse("31/12/2018");
        LOGGER.debug("Parsed Date: {}", date);
        LOGGER.info("END displayDate()");
    }

    @PostConstruct
    public void displayCountry() {
        LOGGER.info("START displayCountry()");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("END displayCountry()");
    }

    @PostConstruct
    public void demonstrateScope() {
        LOGGER.info("START demonstrateScope()");
        Country c1 = context.getBean("prototypeCountry", Country.class);
        Country c2 = context.getBean("prototypeCountry", Country.class);
        LOGGER.debug("Country 1: {}", c1);
        LOGGER.debug("Country 2: {}", c2);
        LOGGER.info("END demonstrateScope()");
    }

    @PostConstruct
    public void displayCountries() {
        LOGGER.info("START displayCountries()");
        countryList.forEach(c -> LOGGER.debug("Country: {}", c));
        LOGGER.info("END displayCountries()");
    }
}