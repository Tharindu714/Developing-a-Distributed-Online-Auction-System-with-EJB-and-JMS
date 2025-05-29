package com.tharindu.me.auctionSystem.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BIDS")
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "bid_time", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bidder_id")
    private User bidder;

    // constructors, getters and setters
}
