package com.company.demo.auth;

import java.util.ArrayList;
import java.util.List;

public class AuthResponse {
    private String email;
    private List<String> roles = new ArrayList<>();
    private String accessToken;

    public AuthResponse() { }

    public AuthResponse(String email, List<String> roles, String accessToken) {
        this.email = email;
        this.roles = roles;
        this.accessToken = accessToken;
    }

    // getters and setters are not shown...

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}