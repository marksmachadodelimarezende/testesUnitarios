package com.mmlrdev.springMVC.service;

import com.mmlrdev.springMVC.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
}
