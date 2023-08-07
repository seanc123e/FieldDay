package org.seancorbett.FieldDay.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.security.UserPrincipal;
import org.seancorbett.FieldDay.service.UserService;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    //loads user, username is actually email
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        System.out.println("USER:::: " + user);
        System.out.println("EMAIL:::: " + username);
        if (user == null) {
            log.warn("Invalid email or password {}", username);

            throw new UsernameNotFoundException("Invalid email or password.");
        }

        return new UserPrincipal(user);
    }

    // CREATE METHODS
    @Transactional
    public void create(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDto, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("USER:::: " + user);
        userRepository.save(user);
    }

    /**    * In this example login and email has the same values @param email @return
     */

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setBranch(userDto.getBranch());
        user.setActive(userDto.getActive());
        user.setPassword(userDto.getPassword());

        // Encrypt the password using Spring Security
        user.setPassword(encoder.encode(userDto.getPassword()));
        System.out.println("USER:::: " + user);
        userRepository.save(user);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setBranch(userDto.getBranch());
        user.setActive(userDto.getActive());
        user.setPassword(userDto.getPassword());
        return userDto;
    }



    //READ METHODS
    public User getUserById() {
        return null;
    }

    public List<Event> getUserEvents(){
        return null;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    //UPDATE METHODS
    public void updateUser(){

    }


}
