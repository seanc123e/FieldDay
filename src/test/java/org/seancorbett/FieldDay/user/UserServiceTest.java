package org.seancorbett.FieldDay.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Test
    public void testUpdateUser(){
        //ARRANGE
        User user = new User("John", "Doe", "email1@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);
        user.setFirstName("Jane");

        //ACT -- UPDATE USER INFO
        userServiceImpl.updateUser(user);

        //ASSERT
        verify(userRepository, times(2)).save(user); // Although two might be unusual, we have to create and save the user first in order to update and then save it
        assertEquals("Jane", user.getFirstName());
    }



}
