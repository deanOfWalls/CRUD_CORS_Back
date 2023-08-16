package com.deanofwalls.CRUD_DEMO.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

//import java.util.Date;
import java.time.LocalDate;

//import org.springframework.web.bind.annotation.*;


//designates as persistent class, maps Entity to table with same name of annotated class
//can be rerouted using @Table annotation. Entities are fungible.
//Each Entity MUST be annotated with a respective ID
@Entity
public class PersonModel {
    @Id //Denotes primary key for this Entity (can be auto-generated or generated manually by app)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotates Id fields, 'IDENTITY' uses Id column
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd") //i added this notation to try to change the format
    private LocalDate birthDate; //I think the format is: yyyy-MM-ddTHH:mm:ss

    public PersonModel() {

    }

    public PersonModel(Long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
