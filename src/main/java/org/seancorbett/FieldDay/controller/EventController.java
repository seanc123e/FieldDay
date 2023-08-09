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
import java.util.List;

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
    public String myEvents(Authentication authentication){
        String username = authentication.getName();
        //getting name of current user
        User user = userService.findUserByUsername(username);
        Host host = user.getHost();
        System.out.println("EVENT_CONTROLLER_HOST::::: " + host);
        List<Event> events = host.getEvents();

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

        // Create and set a new Host or associate with an existing one
        Host host = hostService.findOrCreateHost(user);
        eventDto.setHost(host);

        // Save the event to the database (assuming you have a service to handle this)
        eventService.saveEvent(eventDto);

        // Update the user's hostId
        user.setHost(host);
        userService.updateUser(user);

        return "redirect:/myEvents"; // Redirect to user's events page
    }
}