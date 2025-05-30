package com.tharindu.me.auctionSystem.ejb.Remote;

import com.tharindu.me.auctionSystem.DTO.AuctionDTO;
import jakarta.ejb.Remote;

public interface AuctionManager {
    /**
     * Creates a new auction and schedules its automatic closure.
     * @param dto contains title, startPrice, endTime
     * @return the generated auction ID
     */
    Long createAuction(AuctionDTO dto);

    /**
     * Retrieves auction details by ID.
     * @param auctionId the auction primary key
     * @return AuctionDTO, or null if not found
     */
    AuctionDTO getAuction(Long auctionId);
}
