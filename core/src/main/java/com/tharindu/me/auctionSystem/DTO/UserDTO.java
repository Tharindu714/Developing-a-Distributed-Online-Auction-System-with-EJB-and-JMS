package com.tharindu.me.auctionSystem.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Long id;
    private String username;
    // no passwordHash here for security


    public UserDTO() {
    }

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
