package com.nutrition.sweng.security;

import com.nutrition.sweng.Model.User;
import com.nutrition.sweng.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.withUsername(
                    user.getEmail())
                    .password(user.getPasswordHash())
                    .authorities(user.getRole())
                    .build();
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
