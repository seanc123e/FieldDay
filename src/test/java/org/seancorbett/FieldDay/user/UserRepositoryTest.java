package org.seancorbett.FieldDay.user;
import org.junit.jupiter.api.*;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testFindUserByUsername () {
        //ARRANGE
        User user = new User("John", "Doe", "email1@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);

        //ACT
        User userFound = userRepository.findUserByUsername("email1@email.com");

        //ASSERT
        assertNotNull(userFound); // Make sure the user is not null
        assertEquals("email1@email.com", userFound.getUsername());
    }

}
