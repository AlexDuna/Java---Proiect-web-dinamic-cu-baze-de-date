package com.example.Service;

import com.example.Entity.CustomUserDetails;
import com.example.Entity.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        User user = userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new CustomUserDetails(user);
    }
}
