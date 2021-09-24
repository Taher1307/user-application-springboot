package com.example.userapplication.controller;

import com.example.userapplication.exception.ResourceNotFoundException;
import com.example.userapplication.helpers.Mailer;
import com.example.userapplication.constants.Constants;
import com.example.userapplication.helpers.ResponseConstructor;
import com.example.userapplication.services.UserServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserServices userServices;
    @Autowired
    private Mailer mailer;
    @Autowired
    private ResponseConstructor responseConstructor;

    @RequestMapping(value = Constants.LOGIN, method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody Map<String, String> body) throws ResourceNotFoundException {
        try {
            String username = body.get("username");
            String otpSaved = null;
            otpSaved = userServices.updateOtp(username);

            if (Objects.equals(otpSaved, null)) {
                throw new ResourceNotFoundException(Constants.USER_NOT_FOUND + " " + username);
            }

            mailer.sendMail(username, otpSaved);
            return ResponseEntity.ok().body(responseConstructor.response(Constants.OTP_SENT_SUCCESSFULLY + " " + username));
        } catch (Exception err) {
            log.info(String.valueOf(err));

            if (Objects.equals("java.lang.NullPointerException", String.valueOf(err))) {
                return new ResponseEntity<>(responseConstructor.response(Constants.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(responseConstructor.response(err.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
