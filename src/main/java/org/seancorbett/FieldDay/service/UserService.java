package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName);
    public void create(UserDto userDto);
    public User findUserByEmail(String email);

}
