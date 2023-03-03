package com.example.pshospital.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetails(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails admin = userBuilder.username("Admin").password("qwerty1234").roles("ADMIN").build();
        UserDetails user = userBuilder.username("User").password("qwerty1234").roles("USER").build();
        UserDetails doctor = userBuilder.username("Doctor").password("qwerty1234").roles("DOCTOR").build();

        return new InMemoryUserDetailsManager(admin, user, doctor);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/hospitals").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .and().formLogin().defaultSuccessUrl("/hospitals").permitAll();
        return http.build();

    }
}
