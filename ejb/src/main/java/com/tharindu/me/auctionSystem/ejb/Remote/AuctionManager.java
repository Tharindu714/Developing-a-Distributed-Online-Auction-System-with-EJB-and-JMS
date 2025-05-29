package com.tharindu.me.auctionSystem.ejb.Remote;

import com.tharindu.me.auctionSystem.DTO.AuctionDTO;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AuctionManager {
    AuctionDTO createAuction(AuctionDTO dto);
    List<AuctionDTO> listOpenAuctions();
    void closeAuction(Long auctionId);
}