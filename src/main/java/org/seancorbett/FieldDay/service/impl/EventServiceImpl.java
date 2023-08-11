package org.seancorbett.FieldDay.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.seancorbett.FieldDay.service.EventService;
import org.seancorbett.FieldDay.validation.EventDto;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //CREATING EVENT
    @Override
    @Transactional
    public void create(EventDto eventDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Event event = modelMapper.map(eventDto, Event.class);

//        System.out.println("USER:::: " + event.getRoles().get(0));
        eventRepository.save(event);
    }

    //SAVING EVENT TO DB
    public void saveEvent(EventDto eventDto) {
        System.out.println("HER!");

        Event event = new Event();


        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setPrice(eventDto.getPrice());
        event.setHost(eventDto.getHost());

        System.out.println("SAVED_EVENT:::: " + event);
        eventRepository.save(event);
    }

    //STORING DATA GRABBED INTO THE DTO
    private EventDto mapToEventDto(Event event) {
        EventDto eventDto = new EventDto();

        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setPrice(eventDto.getPrice());
        return eventDto;
    }

    //READ METHODS
    @Override
    public Event findEventById(int id) {
        return eventRepository.findById((long) id).orElse(null);
    }
    @Override
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
}
