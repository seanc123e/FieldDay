package org.seancorbett.FieldDay.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.EventRepository;
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
import org.springframework.web.bind.annotation.*;
import org.seancorbett.FieldDay.config.CustomUserDetails;
import org.seancorbett.FieldDay.service.UserService;
import org.seancorbett.FieldDay.service.impl.EventServiceImpl;

import java.sql.SQLOutput;
import java.util.*;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Field;

@Controller
@RequestMapping("/")
@Slf4j
public class EventController {
    //Controller for all event related mappings
    @Autowired
    private EventRepository eventRepository;

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

        // Update the user's hostId ****FOR OPTIMIZATION MOVE THIS METHOD TO FINDORCREATEHOST()****
        user.setHost(host);
        userService.updateUser(user);

        return "redirect:/myEvents"; // Redirect to user's events page
    }

    @GetMapping("/home")
    public String showEventsHome(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        User existingUserEmail = userService.findUserByUsername(userDto.getUsername());
        User existingUserPassword = userService.findUserByUsername(userDto.getUsername());
        List<Event> randomEvents = eventRepository.shuffleEvents();
        System.out.println("EVENT_CONTROLLER_EVENTS::::: " + randomEvents);
        model.addAttribute("randomEvents", randomEvents);

        return "home";
    }

    //Fetching random events for home page
    @GetMapping("/shuffle")
    @ResponseBody
    public List<Event> fetchRandomEvents() {
        List<Event> randomEvents = eventRepository.shuffleEvents();

       /* <String, Event> events = new HashMap<>();
        events.put("event1", randomEvents.get(0));
        events.put("event2", randomEvents.get(1));
        events.put("event3", randomEvents.get(2));
*/
        System.out.println("EVENT_CONTROLLER_RANDOM_EVENTS::::: " + randomEvents);
        //return randomEvents;
        return randomEvents;
    }

//url path for when a user clicks on a specific event
@GetMapping("/event/{eventId}")
    public String viewEvent(@PathVariable int eventId, Model model){
       Event event = eventService.findEventById(eventId);
         if(event == null){
            return "error";
        } else{
            model.addAttribute("event", event);
        }
        System.out.println("EVENT_CONTROLLER_EVENT_ID:::::: " + event.getEventId());
        return "/viewEvent";
    }

    @GetMapping("/myEvents")
    public String myEvents(Model model, Authentication authentication){
        String username = authentication.getName();
        //getting name of current user
        User user = userService.findUserByUsername(username);
        Host host = user.getHost();
        //System.out.println("EVENT_CONTROLLER_HOST::::: " + host);
        if (host != null) {
            List<Event> events = host.getEvents();

            if(events != null) {
                model.addAttribute("events", events);
            }
        }

        return "myEvents";
    }

    //method for handling DELETING events
    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.DELETE)
    public String deleteEvent(@PathVariable(name = "eventId") int eventId){
        eventService.deleteEvent(eventId);
        System.out.println("EVENTCONTROLLER_DELETE::::: " + eventId);
        return "redirect:/myEvents";
    }

}