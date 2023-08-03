package org.seancorbett.FieldDay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello, Admin";
    }

}
