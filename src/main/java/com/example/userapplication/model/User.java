package com.example.userapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    @Email(message = "email should be a valid email")
    private String username;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String maritalStatus;
    private String password;
    private Long otpTime;

    public User(String firstName, String lastName, String dateOfBirth, String username, String maritalStatus, String password, Long otpTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.maritalStatus = maritalStatus;
        this.password = password;
        this.otpTime = otpTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @JsonIgnore
    public Long getOtpTime() {
        return otpTime;
    }

    @JsonIgnore
    public void setOtpTime(Long otpTime) {
        this.otpTime = otpTime;
    }

    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", username='" + username + '\'' +
            ", maritalStatus=" + maritalStatus +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            '}';
    }

}