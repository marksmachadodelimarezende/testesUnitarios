package com.mmlrdev.springMVC.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/life")
@Slf4j
public class LifeController {

    @GetMapping(value = "/status")
    public ResponseEntity<String> checkLifeApp() {
        log.info("Check /life/status: Backend online");
        return ResponseEntity.ok("Backend online");
    }
}
