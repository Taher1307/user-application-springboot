package com.example.userapplication.constants;

public class Constants {
    //routes
    public static final String API = "/api";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String USERS = "/users";
    public static final String EDIT_USERS = "/users/{id}";
    //error handling
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String USER_DISABLED = "USER_DISABLED";
    public static final String USER_NOT_FOUND = "User not found with username/id: ";
    public static final String USERNAME_CANNOT_BE_NULL = "username is either invalid or it is already registered. Try to register with different username";
    public static final String USERNAME_EXISTS = "Username already exists or invalid";
    public static final String INVALID_OTP = "OTP you have entered is either invalid or expired.";
    public static final String RESOURCE_NOT_FOUND = "Resource not found.";
    //jwt errors
    public static final String JWT_NOT_FOUND = "Unable to get JWT Token";
    public static final String JWT_EXPIRED = "JWT Token has expired";
    public static final String JWT_WARN = "JWT Token does not begin with Bearer String";
    //mail body and subject
    public static final String OTP_SENT_SUCCESSFULLY = "OTP is sent to ";
    public static final String OTP_MESSAGE = "Kindly use this OTP: ";
    public static final String OTP_MESSAGE_TWO = " for verification. \n\n OTP is only valid for 5 minutes.";
    //user actions
    public static final String USER_SAVED_SUCCESSFULLY = "User saved successfully";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    public static final String USER_UPDATED_SUCCESSFULLY = "User updated successfully";
    //constants
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String USER = "User";
    public static final String OTP = "ONE TIME PASSWORD";
    public static final String MESSAGE = "message";
    public static final long JWT_TOKEN_VALIDITY = 6000 * 3;
}
