package com.security.configurations;

import com.security.services.JpaUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JpaUserDetailService service;

    public SecurityConfig(JpaUserDetailService service) {
        this.service = service;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests(auth -> {
                    auth
                            .antMatchers("/api/v1/home").permitAll()
                            .antMatchers("/api/v1/welcome").authenticated()
                            .antMatchers("/api/v1/user").hasAnyRole("USER", "ADMIN")
                            .antMatchers("/api/v1/admin").hasRole( "ADMIN")
                            .anyRequest().authenticated();
                })
                .userDetailsService(service)

                .httpBasic(Customizer.withDefaults())
                .build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
