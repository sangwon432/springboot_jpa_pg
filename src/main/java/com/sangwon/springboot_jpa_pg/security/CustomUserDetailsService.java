package com.sangwon.springboot_jpa_pg.security;

import com.sangwon.springboot_jpa_pg.entities.User;
import com.sangwon.springboot_jpa_pg.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("user not found with username or email " + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
    }


}
