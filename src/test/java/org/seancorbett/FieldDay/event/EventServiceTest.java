package org.seancorbett.FieldDay.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.service.impl.EventServiceImpl;
import org.seancorbett.FieldDay.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @Test
    public void testGetAllEvents(){
        //ARRANGE
        Event event = new Event();
        event.setTitle("bbq night");
        event.setDescription("testDesc");
        event.setLocation("testLocation");
        event.setDate("testDate");
        event.setTime("testTime");
        event.setPrice(10.0);

        eventRepository.save(event);

        //ACT -- UPDATE USER INFO
        List<Event> events = eventServiceImpl.getAllEvents();

        Assertions.assertTrue(events.size() > 0);
    }
}
