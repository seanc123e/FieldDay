package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.validation.EventDto;

import java.util.List;

public interface EventService {

    //CREATE
    public void create(EventDto eventDto);

    void saveEvent(EventDto eventDto);

    //READ
    public Event findEventById(int id);
    public List<Event> getAllEvents();

    //DELETE METHODS
    void deleteEvent(int eventId);
}
