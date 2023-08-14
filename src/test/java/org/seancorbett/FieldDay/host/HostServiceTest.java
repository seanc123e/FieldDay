package org.seancorbett.FieldDay.host;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.seancorbett.FieldDay.repository.HostRepository;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.service.impl.EventServiceImpl;
import org.seancorbett.FieldDay.service.impl.HostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HostServiceTest {

    @Mock
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HostServiceImpl hostServiceImpl;


    @Test
    public void testFindOrCreateHost(){
        //ARRANGE
        User user = new User("John", "Doe", "email1@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);

        Host newHostCreated = hostServiceImpl.findOrCreateHost(user);

        Assertions.assertNotNull(newHostCreated);
    }



}
