package org.seancorbett.FieldDay.service.impl;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.service.EventService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public void saveEvent() {
    //TODO
    }

    @Override
    public Event findEventById() {
        return null;
    }

    @Override
    public List<Event> findAllEvents() {
        return Collections.emptyList();
    }
}
