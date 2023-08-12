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

    @Query(value = "select event_id from fielddaydb.event ", nativeQuery = true) // where event_id in (:id1, :id2, :id3)
    public List<Integer> shuffleEventsByIds();//@Param("id1") long id1, @Param("id2") long id2, @Param("id3") long id3

    @Query(value = "SELECT * FROM events ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Event> shuffleEvents(@Param("limit") int limit);
}
