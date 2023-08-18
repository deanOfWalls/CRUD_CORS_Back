package com.deanofwalls.CRUD_DEMO.controller;

import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import com.deanofwalls.CRUD_DEMO.service.PersonService;
import com.deanofwalls.CRUD_DEMO.service.ServerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://deanofwalls.github.io")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @Autowired
    private ServerStatsService statsService;

    // ... rest of your controller methods


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
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("UP");
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, String>> getStats() throws IOException, InterruptedException {
        Map<String, String> stats = new HashMap<>();
        stats.put("cpuCount", String.valueOf(statsService.getCpuCount()));
//        stats.put("cpuSpeed", statsService.getCpuSpeed() + " MHz");
//        stats.put("ram", statsService.getRam());
        return ResponseEntity.ok(stats);
    }


}
