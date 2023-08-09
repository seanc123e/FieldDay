package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName);

    public User findUserByUsername(String username);

    public void create(UserDto userDto);

    void saveUser(UserDto userDto);

    void updateUser(User user);
//    List<UserDto> findAllUsers();
}
