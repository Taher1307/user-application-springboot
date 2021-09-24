package com.example.userapplication.controller;

import com.example.userapplication.constants.Constants;
import com.example.userapplication.authStrategy.JwtTokenUtil;
import com.example.userapplication.exception.ResourceNotFoundException;
import com.example.userapplication.helpers.ResponseConstructor;
import com.example.userapplication.model.JwtRequest;
import com.example.userapplication.model.JwtResponse;
import com.example.userapplication.model.User;
import com.example.userapplication.services.JwtUserDetailsService;
import com.example.userapplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.userapplication.helpers.OtpTimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private OtpTimer otpChecker;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserServices userServices;
    @Autowired
    private ResponseConstructor responseConstructor;

    @RequestMapping(value = Constants.AUTHENTICATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws ResourceNotFoundException {
        try {
            String otpValid = otpChecker.checkOtpTime(authenticationRequest.getUsername());

            if (otpValid.equals(Constants.USER_NOT_FOUND)) {
                throw new ResourceNotFoundException(Constants.USER_NOT_FOUND + " " + authenticationRequest.getUsername());
            } else if (otpValid == "true") {
                throw new ResourceNotFoundException(Constants.INVALID_CREDENTIALS);
            }

            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            log.info(String.valueOf(e));

            if (Objects.equals("java.lang.NullPointerException", String.valueOf(e)) || Objects.equals("java.lang.Exception", String.valueOf(e))) {
                return new ResponseEntity<>(responseConstructor.response(Constants.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(responseConstructor.response(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = Constants.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userServices.saveUser(user));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(responseConstructor.response(Constants.USERNAME_EXISTS), HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.info(e.getMessage());
            throw new Exception(Constants.USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            log.info(e.getMessage());
            throw new Exception(Constants.INVALID_CREDENTIALS, e);
        }
    }

}