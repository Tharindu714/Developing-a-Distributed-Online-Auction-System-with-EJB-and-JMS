package com.tharindu.me.auctionSystem.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUCTIONS")
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    // constructors, getters and setters
}
