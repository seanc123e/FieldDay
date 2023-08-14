package org.seancorbett.FieldDay.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventRepositoryTest {


    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testShuffleEvents(){
        //ARRANGE
        Event event = new Event();
        event.setTitle("testTitle");
        event.setDescription("testDesc");
        event.setLocation("testLocation");
        event.setDate("testDate");
        event.setTime("testTime");
        event.setPrice(10.0);

        //ACT
        List<Event> events = eventRepository.shuffleEvents();

        //ASSERT
        Assertions.assertEquals(true,events.size() > 0);
    }


    @Test
    public void testFindEventByTitle(){
        //ARRANGE
        Event event = new Event();
        event.setTitle("bbq night");
        event.setDescription("testDesc");
        event.setLocation("testLocation");
        event.setDate("testDate");
        event.setTime("testTime");
        event.setPrice(10.0);

        eventRepository.save(event);

        //ACT
        List<Event> events = eventRepository.findEventsByTitle("night");

        //ASSERT
        Assertions.assertTrue(events.size() > 0);
    }

}
