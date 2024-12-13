package com.msp.library.mgmt.service.impl;

import com.msp.library.mgmt.model.User;
import com.msp.library.mgmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with actual user lookup logic from the database
       /* if (!username.equals("admin")) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles("USER")
                .build();*/
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }
    }
}

