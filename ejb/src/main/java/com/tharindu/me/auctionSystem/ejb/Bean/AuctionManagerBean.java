package com.tharindu.me.auctionSystem.ejb.Bean;


import com.tharindu.me.auctionSystem.DTO.AuctionDTO;
import com.tharindu.me.auctionSystem.Entity.Auction;
import com.tharindu.me.auctionSystem.Entity.User;
import com.tharindu.me.auctionSystem.ejb.Remote.AuctionManager;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.Duration;
import java.time.LocalDateTime;

@Stateless
public class AuctionManagerBean implements AuctionManager {

    @PersistenceContext(unitName = "AuctionUnit")
    private EntityManager em;

    @Resource
    private TimerService timerService;

    @Override
    public Long createAuction(AuctionDTO dto) {
        Auction auction = new Auction();
        auction.setTitle(dto.getTitle());
        auction.setStartPrice(dto.getStartPrice());
        auction.setEndTime(dto.getEndTime());
        User owner = em.getReference(User.class, dto.getOwnerId());
        auction.setOwner(owner);
        em.persist(auction);

        // schedule a timer to close the auction exactly at endTime
        Duration delay = Duration.between(LocalDateTime.now(), dto.getEndTime());
        timerService.createTimer(delay.toMillis(), auction.getId());

        return auction.getId();
    }

    @Timeout
    public void onTimeout(Timer timer) {
        Long auctionId = (Long) timer.getInfo();
        Auction auction = em.find(Auction.class, auctionId);
        if (auction != null) {
            // close logic: here simply mark endTime in the past
            auction.setEndTime(LocalDateTime.now());
            em.merge(auction);
        }
    }

    @Override
    public AuctionDTO getAuction(Long id) {
        Auction auction = em.find(Auction.class, id);
        if (auction == null) return null;
        AuctionDTO dto = new AuctionDTO();
        dto.setId(auction.getId());
        dto.setTitle(auction.getTitle());
        dto.setStartPrice(auction.getStartPrice());
        dto.setEndTime(auction.getEndTime());
        // use the correct getter from your User entity:
        dto.setOwnerId(auction.getOwner().getId());
        return dto;
    }
}
