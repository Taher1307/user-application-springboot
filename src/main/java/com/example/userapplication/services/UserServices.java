package com.example.userapplication.services;

import com.example.userapplication.constants.Constants;
import com.example.userapplication.exception.ResourceNotFoundException;
import com.example.userapplication.helpers.ResponseConstructor;
import com.example.userapplication.model.User;
import com.example.userapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ResponseConstructor responseConstructor;

    public String updateOtp(String email) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(email);
        if (user == null || !Objects.equals(user.getUsername(), email)) {
            new ResourceNotFoundException(Constants.USER_NOT_FOUND + " " + email);
        }
        Random random = new Random();
        Integer otp = Math.abs(random.nextInt(100000));
        userRepository.findById(user.getId())
            .map(user1 -> {
                user1.setPassword(bcryptEncoder.encode(otp.toString()));
                user1.setOtpTime(new Date().getTime());
                return userRepository.save(user);
            }).orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND + " " + email));
        return otp.toString();
    }

    public User updateUser(User newUser, Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
            .map(user -> {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setDateOfBirth(newUser.getDateOfBirth());
                user.setUsername(newUser.getUsername());
                user.setMaritalStatus(newUser.getMaritalStatus());
                return userRepository.save(user);
            }).orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findOneUser(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND));
    }

    public Map<String, String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return responseConstructor.response(Constants.USER_DELETED_SUCCESSFULLY);
    }
}
