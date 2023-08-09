package org.seancorbett.FieldDay.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.service.EventService;
import org.seancorbett.FieldDay.service.HostService;
import org.seancorbett.FieldDay.validation.EventDto;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.seancorbett.FieldDay.config.CustomUserDetails;
import org.seancorbett.FieldDay.service.UserService;
import org.seancorbett.FieldDay.service.impl.EventServiceImpl;

import java.sql.SQLOutput;

@Controller
@RequestMapping("/")
@Slf4j
public class EventController {

    @Autowired
    private final EventService eventService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final HostService hostService;

    @Autowired
    public EventController(EventService eventService, UserService userService, HostService hostService) {
        this.eventService = eventService;
        this.userService = userService;
        this.hostService = hostService;
    }

    @GetMapping("/myEvents")
    public String myEvents(){
        return "myEvents";
    }

    @GetMapping("/createEvent")
    public String createEvent(Model model){
        model.addAttribute("event", new EventDto());
        return "createEvent";
    }

    @PostMapping("/createEvent")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, Authentication authentication, BindingResult result) { //changed , BindingResult result, Model model to Authentication authentication
        System.out.println("Controller Cheese");
        // Handle validation errors
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "home";
        }

        String username = authentication.getName(); //getting name of current user
        User user = userService.findUserByUsername(username);

        // Create a new Host or associate with an existing one
        Host host = hostService.findOrCreateHost(user);

        eventDto.setHost(host);
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Create the event and associate it with the logged-in user
        //User user = userService.findUserByUsername(userDetails.getUsername());
         // Associate the event with the user
        System.out.println("EVENT_CONTROLLER_HOST::::: " + eventDto.getHost());
        // Save the event to the database (assuming you have a service to handle this)
        eventService.saveEvent(eventDto);

        return "redirect:/myEvents"; // Redirect to user's events page
    }
}


  /*  User existingUser = userService.findUserByUsername(userDto.getUsername());
        System.out.println("USER:::   " + userDto);
                if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
                result.rejectValue("username", null, "There is already an account registered with the same email");
                }

                if (result.hasErrors()) {
                model.addAttribute("user", userDto);

                return "/signup";
                }

                userService.create(userDto);
                return "redirect:/signup?success";*/