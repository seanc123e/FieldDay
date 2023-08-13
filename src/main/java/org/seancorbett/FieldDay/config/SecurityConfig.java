package org.seancorbett.FieldDay.config;

import org.seancorbett.FieldDay.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import static javax.management.Query.and;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userDetailsService;

    //Inserted to allow the post method for delete event to actually be a delete method.
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    //beans
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
//TODO - finish authentication and authorization

    //security filter chain allowing access to pages
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> {
                    authz
                        .requestMatchers(requestMatcher("/signup")).permitAll()//, "/images/**" "/css/**)
                        .requestMatchers(requestMatcher("/signup/save")).permitAll()
                        .requestMatchers(requestMatcher("/images/**")).permitAll()
                        .requestMatchers(requestMatcher("/css/**")).permitAll()
                        .requestMatchers(requestMatcher("/js/**")).permitAll()
                        .requestMatchers(requestMatcher( "/shuffle" )).permitAll()
                        .requestMatchers(requestMatcher("/home")).permitAll()
                        .requestMatchers(requestMatcher("/createEvent", "/myEvents", "/events","/event/**"))//"/js/**", "/home", ,"/shuffle"
                        .hasAnyRole("USER_ROLE")
                        .anyRequest()
                        .authenticated();
                })
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                );
        return http.build();
    }

    private RequestMatcher requestMatcher(String... patterns) {
        String pattern = String.join(",", patterns);
        System.out.println("PATTERN:::: " + pattern);
        return new AntPathRequestMatcher(pattern);
    }
}
