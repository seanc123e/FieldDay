package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.validation.EventDto;

public interface EventService {

    public Event findEventById(int id);

    public void create(EventDto eventDto);

    void saveEvent(EventDto eventDto);

}
