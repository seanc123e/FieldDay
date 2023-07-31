package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById (Long eventId);

}
