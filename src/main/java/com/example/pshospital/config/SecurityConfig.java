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

        UserDetails admin = userBuilder.username("admin").password("1234").roles("ADMIN").build();
        UserDetails user = userBuilder.username("user").password("1234").roles("USER").build();
        UserDetails doctor = userBuilder.username("doctor").password("1234").roles("DOCTOR").build();

        return new InMemoryUserDetailsManager(admin, user, doctor);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/hospitals").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("{hospitalId}/deleteHospital").hasAnyRole("ADMIN")
                .requestMatchers("/hospitals/newHospital").hasAnyRole("ADMIN")
                .requestMatchers("/hospitals/saveHospital").hasAnyRole("ADMIN")
                .requestMatchers("/hospitals/{id}/updateHospital").hasAnyRole("ADMIN")

                .requestMatchers("/departments{hospitalId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/newDepartment/{hospitalId}").hasAnyRole("ADMIN")
                .requestMatchers("/departments/saveDepartment/{hospitalId}").hasAnyRole("ADMIN")
                .requestMatchers("/departments/{hospitalId}/deleteDepartment/{departmentId}").hasAnyRole("ADMIN")
                .requestMatchers("/departments/{hospitalId}/editDepartment/{id}").hasAnyRole("ADMIN")
                .requestMatchers("/departments/{hospitalId}/updateDepartment/{id}").hasAnyRole("ADMIN")

                .requestMatchers("/doctors/{departmentId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/doctors/newDoctor/{departmentId}").hasAnyRole("ADMIN")
                .requestMatchers("/doctors/saveDoctor/{departmentId}").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers("/doctors/{departmentId}/deleteDoctor/{doctorId}").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers("/doctors/{departmentId}/editDoctor/{id}").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers("/doctors/{departmentId}/updateDoctor/{id}").hasAnyRole("ADMIN", "DOCTOR")

                .requestMatchers("/appointments/{doctorId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/appointments/{doctorId}/deleteAppointment/{appointmentId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/appointments/newAppointment/{doctorId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/appointments/saveAppointment/{doctorId}").hasAnyRole("ADMIN", "USER", "DOCTOR")

                .requestMatchers("/patients/{appointmentId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/patients/newPatient/{appointmentId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/patients/savePatient/{appointmentId}").hasAnyRole("ADMIN", "USER", "DOCTOR")
                .requestMatchers("/patients/{appointmentId}/deletePatient/{patientId}").hasAnyRole("ADMIN", "USER", "DOCTOR")

                .and().formLogin().defaultSuccessUrl("/hospitals").permitAll();
        return http.build();
    }
}
