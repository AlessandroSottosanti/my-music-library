package org.lessons.spring.my_music_library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/", "/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/songs/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/artists/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/albums/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .defaultSuccessUrl("/albums", true)  // dopo login porta qui SEMPRE
                .permitAll()
            .and()
            .logout()
                .permitAll()
            .and()
            .exceptionHandling()
            .and()
            .csrf().disable();

        return http.build();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Collegamento al servizio che recupera gli utenti dal database
        authProvider.setUserDetailsService(userDetailService());

        // Imposta un password encoder (uso di delegazione dei password encoder)
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        // Aggiungi il tuo servizio che carica gli utenti dal database
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // Usa il delegating password encoder per la gestione delle password
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
