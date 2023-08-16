package com.deanofwalls.CRUD_DEMO.controller;


import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import com.deanofwalls.CRUD_DEMO.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://deanofwalls.github.io/CRUD_CORS_Front/")
@Controller
public class PersonController {
    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PersonModel> create(@RequestBody PersonModel person) {
        return new ResponseEntity<>(service.create(person), HttpStatus.CREATED);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<PersonModel> readById(@PathVariable Long id) {
        return new ResponseEntity<>(service.readById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/readAll")
    public ResponseEntity<List<PersonModel>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PersonModel> updateById(
            @PathVariable Long id,
            @RequestBody PersonModel newData) {
        return new ResponseEntity<>(service.update(id, newData), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<PersonModel> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/health-check")
    public ResponseEntity<String>healthCheck(){
        return ResponseEntity.ok("UP");
    }


}
