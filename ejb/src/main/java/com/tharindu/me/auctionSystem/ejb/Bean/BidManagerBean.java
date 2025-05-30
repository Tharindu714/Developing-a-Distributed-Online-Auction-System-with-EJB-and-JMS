package com.tharindu.me.auctionSystem.ejb.Bean;


import com.tharindu.me.auctionSystem.DTO.BidDTO;
import com.tharindu.me.auctionSystem.Entity.Auction;
import com.tharindu.me.auctionSystem.Entity.Bid;
import com.tharindu.me.auctionSystem.Entity.User;
import com.tharindu.me.auctionSystem.ejb.Remote.BidManager;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Stateless
public class BidManagerBean implements BidManager {

    @PersistenceContext(unitName = "AuctionUnit")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void placeBid(BidDTO dto) {
        // 1. Load auction with optimistic lock
        Auction auction = em.find(Auction.class, dto.getAuctionId(), LockModeType.OPTIMISTIC);

        // 2. Compute minimum allowed bid (flat increment of 5% here)
        BigDecimal increment = auction.getStartPrice().multiply(new BigDecimal("0.05"));
        BigDecimal minAllowed = auction.getStartPrice().max(auction.getStartPrice().add(increment));

        if (dto.getAmount().compareTo(minAllowed) < 0) {
            throw new IllegalArgumentException("Bid too low; must be ≥ " + minAllowed);
        }

        // 3. Persist the bid
        Bid bid = new Bid();
        bid.setAmount(dto.getAmount());
        bid.setTimestamp(LocalDateTime.now());
        bid.setAuction(auction);
        User bidder = em.getReference(User.class, dto.getBidderId());
        bid.setBidder(bidder);
        em.persist(bid);

        // 4. Update auction's current bid
        auction.setStartPrice(dto.getAmount());  // assume startPrice holds current highest
        em.merge(auction);
    }
}
