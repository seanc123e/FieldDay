package org.seancorbett.FieldDay.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.seancorbett.FieldDay.model.Branch;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.RoleRepository;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.service.impl.RoleServiceImpl;
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
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;



    @Test
    public void testGetAllRoles(){
        //ACT -- UPDATE USER INFO
        List<Role> roles = roleServiceImpl.getAllRoles();

        //ASSERT
        Assertions.assertTrue(roles.size() > 0);
    }

}
