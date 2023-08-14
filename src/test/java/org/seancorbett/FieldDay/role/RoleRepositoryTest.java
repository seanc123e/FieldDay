package org.seancorbett.FieldDay.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.EventRepository;
import org.seancorbett.FieldDay.repository.RoleRepository;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindRoleByName(){
        //ACT
        Role roleFound = roleRepository.findRoleByName("USER_ROLE");

        //ASSERT
        Assertions.assertNotNull(roleFound);
    }

    @Test
    public void testFindRoleByUser(){
//        @Query(value = "select * from role where role.id= (select role_id from users_roles where user_id = :id)", nativeQuery = true)
//        public List<Role> findRoleByUser(@Param("id") long id);
        //ACT
        User user = userRepository.findUserByUsername("test@test.com");
        List<Role> roleFound = roleRepository.findRoleByUser((long)user.getUserId());

        //ASSERT
        Assertions.assertNotNull(roleFound);
    }
}
