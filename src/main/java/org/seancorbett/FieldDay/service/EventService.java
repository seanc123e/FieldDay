package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Event;

import java.util.List;

public interface EventService {

    void saveEvent();
    Event findEventById();
    List<Event> findAllEvents();
}
