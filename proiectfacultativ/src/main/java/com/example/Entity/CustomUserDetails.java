package com.example.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private List<GrantedAuthority> roles;

    public CustomUserDetails(User user) {
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.first_name=user.getFirst_name();
        this.last_name=user.getLast_name();
        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("User role cannot be null or empty");
        }
        this.roles = Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    public String get_FullName(User user){
        return user.getFirst_name() + " " + user.getLast_name();
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }
}

