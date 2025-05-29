package com.tharindu.me.auctionSystem.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidDTO implements Serializable {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private Long auctionId;
    private Long bidderId;

    public BidDTO() {
    }

    public BidDTO(Long id, BigDecimal amount, LocalDateTime timestamp, Long auctionId, Long bidderId) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.auctionId = auctionId;
        this.bidderId = bidderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getBidderId() {
        return bidderId;
    }

    public void setBidderId(Long bidderId) {
        this.bidderId = bidderId;
    }
}
