package com.aspromedic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.aspromedic.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/gestion/guardarEmpresa").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cargo/save").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cargo/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cargo/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ciudad/save").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ciudad/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ciudad/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/departamento/save").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/departamento/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/departamento/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/param/cargos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/param/ciudades").permitAll()
                        .requestMatchers(HttpMethod.GET, "/gestion/codigo").permitAll()
                        .requestMatchers(HttpMethod.GET, "/gestion/misContactos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/gestion4/filtrar/dosimetros").permitAll()
                        .requestMatchers(
                            "/user/**", 
                            "/admin/**", 
                            "/gestion/**"
                            ).authenticated()
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/access", true))
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF si es necesario

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
