package com.example.userapplication.model;

public class JwtResponse {

    private String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return jwttoken;
    }

}