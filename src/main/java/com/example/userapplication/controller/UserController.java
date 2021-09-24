package com.example.userapplication.controller;

import java.util.List;
import java.util.Optional;

import com.example.userapplication.constants.Constants;
import com.example.userapplication.exception.ResourceNotFoundException;
import com.example.userapplication.helpers.ResponseConstructor;
import com.example.userapplication.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.userapplication.model.User;

@RestController
@RequestMapping(Constants.API)
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userService;

    @Autowired
    private ResponseConstructor responseConstructor;

    @RequestMapping(value = Constants.USERS, method = RequestMethod.GET)
    List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = Constants.USERS, method = RequestMethod.POST)
    ResponseEntity<?> saveNewUser(@RequestBody User newUser) {
        try {
            userService.saveUser(newUser);
            return new ResponseEntity<>(responseConstructor.response(Constants.USER_SAVED_SUCCESSFULLY), HttpStatus.resolve(200));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(responseConstructor.response(Constants.USERNAME_EXISTS), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = Constants.EDIT_USERS, method = RequestMethod.GET)
    ResponseEntity<Optional<User>> findOneUser(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userService.findOneUser(id));
        if (user.isPresent()) return ResponseEntity.ok().body(user);
        else throw new ResourceNotFoundException(Constants.USER_NOT_FOUND + " " + id);
    }


    @RequestMapping(value = Constants.EDIT_USERS, method = RequestMethod.PUT)
    ResponseEntity<?> editUser(@RequestBody User newUser, @PathVariable Long id) {
        try {
            User userUpdated = userService.updateUser(newUser, id);
            return ResponseEntity.ok().body(userUpdated);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(responseConstructor.response(Constants.USER_NOT_FOUND + " " + id), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = Constants.EDIT_USERS, method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.deleteUser(id));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(responseConstructor.response(Constants.USER_NOT_FOUND + " " + id), HttpStatus.BAD_REQUEST);
        }
    }
}
