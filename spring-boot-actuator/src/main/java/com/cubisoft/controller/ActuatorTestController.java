package com.cubisoft.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/actuator")
@Slf4j
public class ActuatorTestController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("Health endpoint called");
        return ResponseEntity.ok("SUCCESS: Health endpoint working");
    }

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        log.info("Info endpoint called");
        return ResponseEntity.ok("SUCCESS: Info endpoint working");
    }

    @GetMapping("/metrics")
    public ResponseEntity<String> metrics() {
        log.info("Metrics endpoint called");
        return ResponseEntity.ok("SUCCESS: Metrics endpoint working");
    }

    @GetMapping("/beans")
    public ResponseEntity<String> beans() {
        log.info("Beans endpoint called");
        return ResponseEntity.ok("SUCCESS: Beans endpoint working");
    }

    @GetMapping("/env")
    public ResponseEntity<String> env() {
        log.info("Env endpoint called");
        return ResponseEntity.ok("SUCCESS: Env endpoint working");
    }

    @GetMapping("/mappings")
    public ResponseEntity<String> mappings() {
        log.info("Mappings endpoint called");
        return ResponseEntity.ok("SUCCESS: Mappings endpoint working");
    }

    @GetMapping("/all")
    public ResponseEntity<String> all() {
        log.info("All actuator test endpoints called");

        String response =
                "SUCCESS:\n" +
                        "- Health OK\n" +
                        "- Info OK\n" +
                        "- Metrics OK\n" +
                        "- Beans OK\n" +
                        "- Env OK\n" +
                        "- Mappings OK";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
