package com.example.football.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Thêm import HttpMethod
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/welcome", "/login", "/logout", "/logoutSuccessful", "/403", "/css/**", "/js/**", "/images/**").permitAll()

                .requestMatchers("/players", "/players/{code}", "/players/search", "/players/set-view").permitAll() 

                .requestMatchers("/teams", "/teams/{teamId}").permitAll()

                .requestMatchers(HttpMethod.GET, "/v1/api/players", "/v1/api/players/**").permitAll()

                .requestMatchers("/players/favorite-team", "/players/add-to-favorite/**", "/players/remove-from-favorite/**").hasRole("USER")

                .requestMatchers("/players/add", "/players/edit/**", "/players/delete/**").hasRole("ADMIN")

                .requestMatchers("/team/team-add", "/teams/edit/**", "/teams/delete/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/v1/api/players").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/v1/api/players/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/v1/api/players/**").hasRole("ADMIN")

                .requestMatchers("/admin/**").hasRole("ADMIN")

                .requestMatchers("/userInfo").hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/userInfo", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
        );

        http.logout(form -> form
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
        );

        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));
        return http.build();
    }
}