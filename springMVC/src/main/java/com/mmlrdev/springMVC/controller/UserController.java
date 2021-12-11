package com.mmlrdev.springMVC.controller;

import com.mmlrdev.springMVC.entities.User;
import com.mmlrdev.springMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        if (Objects.isNull(users))
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(users);
    }
}

