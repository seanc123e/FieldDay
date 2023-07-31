package org.seancorbett.FieldDay.controller;

import org.seancorbett.FieldDay.model.Event;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorized")
public class EventController {

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public Event createEvent(){

        return null;
    }
}
