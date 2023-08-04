package org.seancorbett.FieldDay.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.service.impl.UserServiceImpl;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private UserServiceImpl userDetailsService;

    @Autowired
    public HomeController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/")
    private String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute ("userDto") UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.warn("Wrong attempt");
            return "sign-up";
        }
        userDetailsService.create(userDto);
        return "confirmation";
    }
}
