package com.ftn.MyHousebackend.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

    String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}