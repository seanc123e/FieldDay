package org.seancorbett.FieldDay.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.seancorbett.FieldDay.config.CustomUserDetails;
import org.seancorbett.FieldDay.model.Event;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.UserRepository;
import org.seancorbett.FieldDay.security.UserPrincipal;
import org.seancorbett.FieldDay.service.UserService;
import org.seancorbett.FieldDay.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //********DEPENDENCY INJECTION********
    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //********CONSTRUCTOR********
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    //********CREATE AND SAVE METHODS********
    @Transactional
    public void create(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleRepository.findRoleByName("USER_ROLE");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));

        System.out.println("USER:::: " + user.getRoles().get(0));
        userRepository.save(user);
    }

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

        Role role = roleRepository.findRoleByName("USER_ROLE");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        System.out.println("USER:::: " + user.getRoles().get(0));
        userRepository.save(user);
    }

    //********READ METHODS********
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        System.out.println("USER:::: " + user.getRoles().get(0));
        System.out.println("EMAIL:::: " + username);
        if (user == null) {
            log.warn("Invalid email or password {}", username);

            throw new UsernameNotFoundException("Invalid email or password.");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        CustomUserDetails userDetails = new CustomUserDetails("username", "encodedPassword", userRepository, authorities);

        return new UserPrincipal(user);
    }
    public User getUserById() {
        return null;
    }

    public List<Event> getUserEvents(){
        return null;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("USER_ROLE");
        return roleRepository.save(role);
    }

    //********UPDATE METHODS********
    public void updateUser(User user){
        userRepository.save(user);
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
}
