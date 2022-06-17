package com.ftn.MyHousebackend.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;

    private String username;

    private String roles;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

}