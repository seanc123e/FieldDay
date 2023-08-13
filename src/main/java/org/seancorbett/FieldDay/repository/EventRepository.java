package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM fielddaydb.event ORDER BY RAND() LIMIT 3", nativeQuery = true)
    public List<Event> shuffleEvents();

    //queries the database for whatever the user searches for in the search bar
    @Query(value = "SELECT * FROM fielddaydb.event WHERE title LIKE '%:search%'", nativeQuery = true)
    public List<Event> findEventByTitle(String search);

}
