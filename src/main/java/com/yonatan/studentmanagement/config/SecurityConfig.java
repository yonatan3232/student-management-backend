package com.yonatan.studentmanagement.config;

import com.yonatan.studentmanagement.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Login does not require authentication
                        .requestMatchers("/login").permitAll()

                        // Dashboard statistics
                        .requestMatchers("/dashboard/stats").permitAll()

                        // Student Management
                        .requestMatchers("/students/**").permitAll()

                        // Department Management
                        .requestMatchers("/departments/**").permitAll()

                        // Course Management
                        .requestMatchers("/courses/**").permitAll()

                        // Enrollment Management
                        .requestMatchers("/enrollments/**").permitAll()

                        // Academic Records
                        .requestMatchers("/academic-records/**").permitAll()

                        // Attendance
                        .requestMatchers("/attendance/**").permitAll()

                        // Users and Roles remain protected
                        .requestMatchers("/users/**", "/roles/**").hasRole("ADMIN")

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}