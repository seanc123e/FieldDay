package org.seancorbett.FieldDay;
import org.junit.jupiter.api.*;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindUserByUsername () {
        //ARRANGE
        User user = new User("John", "Doe", "email@email.com", "password", Branch.MARINE_CORPS, true, null,  null);
        userRepository.save(user);

        //ACT
        User userFound = userRepository.findUserByUsername("email@email.com");

        //ASSERT
        Assertions.assertInstanceOf(User.class, userFound);
    }
}
