package org.seancorbett.FieldDay.service.impl;

import lombok.extern.slf4j.Slf4j;
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

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    /*private final VeteranRepository veteranRepository;
    private final BCryptPasswordEncoder encoder;
    @Autowired
    public VeteranServiceImpl(VeteranRepository veteranRepository, BCryptPasswordEncoder encoder) {
        this.veteranRepository = veteranRepository;
        this.encoder = encoder;
    }*/
//    LOADS USER BY EMAIL---OVERRIDDEN USERNAME METHOD
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        /*log.debug(email);
        System.out.println("************************************");
        System.out.println("UserName: " + email);
        System.out.println("************************************");*/
        if (user == null) {
            log.warn("Invalid username or password {}", email);

            throw new UsernameNotFoundException("Invalid email or password.");
        }

//        System.out.println("User: " + user + "User ID: " + );
//        return new VeteranPrincipal(veteran, roleService.getRolesByUser(veteran.getId()));
        return new UserPrincipal(user);
    }

    @Transactional
    public void create(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDto, User.class);

        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    /**    * In this example login and email has the same values @param email @return
     */
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
