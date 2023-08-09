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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static javax.management.Query.and;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userDetailsService;

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
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                /*.authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers(requestMatcher("/", "/login*", "/css/*", "/js/*", "/signup", "/signup-process", "/error"))
                                //.antMatchers("/login", "/static/**", "/signup", "/signup-process")
                                .permitAll()
                                .requestMatchers(requestMatcher("/", "/home*", "/createEvent", "/myEvents", "/profile")) //antmatchers and use home and use roles
                                //.antMatchers("/", "/home", "/createEvent", "/myEvents", "/profile")
                                .authenticated()
                )
                *//*.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(requestMatcher("/",  "/createEvent", "/myEvents", "/profile")) // "/home**",antmatchers and use home and use roles
                                .antMatchers("/", "/createEvent", "/myEvents", "/profile")
                                .authenticated()
                                .anyRequest()
                                .denyAll()
                )*//*
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                //.logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                //.deleteCookies("JSESSIONID")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )*/
            //TODO DEFINE RELATIONSHIP FOR ROLES CLASS, ADD ROLES INTO SQL TABLE
        http
            .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> {
                    authz
                        .requestMatchers(requestMatcher("/signup/**")).permitAll()
//                        .requestMatchers(requestMatcher("/login")).permitAll()
                        .requestMatchers(requestMatcher("/css/**")).permitAll()
    //                            .requestMatchers(requestMatcher("/home")).permitAll();
                        .requestMatchers(requestMatcher("/home")).hasRole("USER_ROLE");
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
                                .permitAll());
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling
//                                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                );
        return http.build();
    }

    private RequestMatcher requestMatcher(String... patterns) {
        String pattern = String.join(",", patterns);
        return new AntPathRequestMatcher(pattern);
    }
}
