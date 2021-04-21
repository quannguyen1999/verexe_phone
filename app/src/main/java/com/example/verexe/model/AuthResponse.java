package com.example.verexe.model;

public class AuthResponse {
    private String token;
    private String refreshToken;
    private UserType userType;

    public AuthResponse() {
    }

    public AuthResponse(String token, String refreshToken, UserType userType) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userType=" + userType +
                '}';
    }
}
