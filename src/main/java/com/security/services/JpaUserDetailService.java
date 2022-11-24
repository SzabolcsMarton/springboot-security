package com.security.services;

import com.security.exceptions.UserNotFoundException;
import com.security.model.SecurityUser;
import com.security.model.User;
import com.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Can not find user with email: " + email));
        return new SecurityUser(user);
    }
}
