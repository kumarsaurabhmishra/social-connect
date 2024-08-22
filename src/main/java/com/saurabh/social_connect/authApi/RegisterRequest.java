package com.saurabh.social_connect.authApi;

import com.saurabh.social_connect.enums.Role;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Character gender;
    private Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String email, String password, Character gender, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
