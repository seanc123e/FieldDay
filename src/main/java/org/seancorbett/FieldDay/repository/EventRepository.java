package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    //READ
 /*   public Event findEventByEventId(int eventId);

    public List<Event> getAllEvents();*/

}
