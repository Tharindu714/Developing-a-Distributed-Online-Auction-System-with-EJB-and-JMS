package com.tharindu.me.auctionSystem.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;


}