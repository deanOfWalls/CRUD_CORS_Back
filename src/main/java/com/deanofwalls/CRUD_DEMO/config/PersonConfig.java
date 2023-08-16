package com.deanofwalls.CRUD_DEMO.config;

import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import com.deanofwalls.CRUD_DEMO.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class PersonConfig {
    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void setup() {
        PersonModel person1 = new PersonModel();
        person1.setFirstName("Guy");
        person1.setLastName("Fieri");
        person1.setBirthDate(LocalDate.of(1999,10,10));


        PersonModel person2 = new PersonModel();
        person2.setFirstName("Dean");
        person2.setLastName("Walls");

        repository.saveAll(Arrays.asList(
                person1,
                person2
        ));
    }
}
