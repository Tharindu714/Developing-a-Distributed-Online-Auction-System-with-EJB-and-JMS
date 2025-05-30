package com.tharindu.me.auctionSystem.ejb.Remote;

import com.tharindu.me.auctionSystem.DTO.BidDTO;
import jakarta.ejb.Remote;

@Remote
public interface BidManager {
    /**
     * Places a bid on the given auction.
     * @param dto contains auctionId, bidderId, amount
     * @throws IllegalArgumentException if bid is too low
     */
    void placeBid(BidDTO dto);
}

