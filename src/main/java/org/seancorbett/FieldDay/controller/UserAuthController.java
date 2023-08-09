package org.seancorbett.FieldDay.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.service.UserService;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
@Controller
@RequestMapping("/")
@Slf4j
public class UserAuthController {
    private UserService userService;

    @Autowired
    public UserAuthController(UserService userService)    {
        this.userService = userService;
    }

    //login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //registration form request
    @GetMapping("/signup")
    public String showSignupForm(Model model) {

        // create a model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "signup";
    }

    //handle registration from submit request
    @PostMapping("/signup/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByUsername(userDto.getUsername());
        System.out.println("USER:::   " + userDto);
        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);

            return "/signup";
        }

        userService.create(userDto);
        return "redirect:/signup?success";

    }

    //mapping for home request
    @GetMapping("/home")
    public String home(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        System.out.println(userDto);
//        User existingUserEmail = userService.findUserByUsername(userDto.getUsername());
//        User existingUserPassword = userService.findUserByUsername(userDto.getUsername());
//        if(existingUserEmail != null ){
//            return "home";
//        }
//        return "redirect:/login";
        return "home";
    }

    //ERROR PAGE FOR ERROR HANDLING
    @GetMapping("/error")
    public String error(){
        return "error";
    }

    // handler method is used to handle a list of users
    /*@GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();

        model.addAttribute("users", users);

        return "users";

    }*/
}
