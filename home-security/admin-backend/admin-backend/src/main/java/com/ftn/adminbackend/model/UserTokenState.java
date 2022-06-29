package com.ftn.adminbackend.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;

    private String username;

    private String role;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

}