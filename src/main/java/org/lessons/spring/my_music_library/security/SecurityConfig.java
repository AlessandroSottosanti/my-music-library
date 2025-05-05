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
                .requestMatchers("/songs/**", "/artists/**", "/albums/**", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/api/**").permitAll()    // da configurare auth user
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/songs", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403")
                .and()
                .csrf().disable() // disabilitato per semplificare l'accesso API REST
                .httpBasic(); // <--- ABILITA BASIC AUTH per client esterni come Postman o fetch()
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
