package org.seancorbett.FieldDay.host;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.HostRepository;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HostRepositoryTest {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUser () {
        //ARRANGE
        User user = new User("John", "Doe", "email1@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);
        Host host = new Host();
        host.setUser(user);
        hostRepository.save(host);

        //ACT
        Host hostFound = hostRepository.findByUser(user);

        //ASSERT
        assertNotNull(hostFound); // Make sure the user is not null
//        assertEquals("email1@email.com", hostFound.getUsername());
    }
    @Test
    public void testSave () {
        //ARRANGE
        User user = new User("John", "Doe", "email1@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);
        Host host = new Host();
        host.setUser(user);


        //ACT
        Host hostSaved = hostRepository.save(host);

        //ASSERT
        assertNotNull(hostSaved); // Make sure the user is not null
//        assertEquals("email1@email.com", hostFound.getUsername());
    }
}
