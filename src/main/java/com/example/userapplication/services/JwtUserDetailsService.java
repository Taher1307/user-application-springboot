package com.example.userapplication.services;

import java.util.ArrayList;

import com.example.userapplication.constants.Constants;
import com.example.userapplication.model.User;
import com.example.userapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
            new ArrayList<>());
    }

}
