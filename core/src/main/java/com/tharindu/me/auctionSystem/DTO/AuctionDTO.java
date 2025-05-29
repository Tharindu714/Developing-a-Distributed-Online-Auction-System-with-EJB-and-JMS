package com.tharindu.me.auctionSystem.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionDTO implements Serializable {
    private Long id;
    private String title;
    private BigDecimal startPrice;
    private LocalDateTime endTime;
    private Long ownerId;

    public AuctionDTO() {
    }

    public AuctionDTO(Long id, String title, BigDecimal startPrice, LocalDateTime endTime, Long ownerId) {
        this.id = id;
        this.title = title;
        this.startPrice = startPrice;
        this.endTime = endTime;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
