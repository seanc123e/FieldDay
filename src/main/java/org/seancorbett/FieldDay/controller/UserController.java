package org.seancorbett.FieldDay.controller;

import org.seancorbett.FieldDay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepo;

    @GetMapping
    public String home(){
        return "Hello, JWT!";
    }
}
