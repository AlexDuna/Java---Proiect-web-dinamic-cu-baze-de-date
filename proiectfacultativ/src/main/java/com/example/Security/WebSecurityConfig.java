package com.example.Security;

import com.example.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    SecurityFilterChain configurer(HttpSecurity http, AuthenticationSuccessHandler successHandler) throws Exception{

        http.authorizeHttpRequests(auth->auth
                        .requestMatchers("/register", "/process_register", "/login", "/").permitAll()
                        .requestMatchers("/masini/user").hasAuthority("ROLE_USER")
                        .requestMatchers("/masini/view").hasAuthority("ROLE_EDITOR")
                .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(login->login
                        .loginPage("/login")
                        .usernameParameter("username")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(logout->logout.logoutSuccessUrl("/").permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}