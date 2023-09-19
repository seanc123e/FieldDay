package org.seancorbett.FieldDay.service.impl;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.util.List;

@Service
public class SearchServiceImpl {
    @Autowired
    EventRepository eventRepository;

    public List<Event> search(String query){
        List<Event> results = eventRepository.findEventsByTitle(query);

        return results;
    }
}
