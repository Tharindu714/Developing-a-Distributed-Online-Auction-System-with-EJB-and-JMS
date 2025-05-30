package com.tharindu.me.auctionSystem.ejb.Bean;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class AuctionTimerBean {

    @PersistenceContext(unitName = "AuctionUnit")
    private EntityManager em;

    // runs every minute
    @Schedule(hour="*", minute="*", second="0", persistent=false)
    public void checkExpiredAuctions() {
        em.createQuery("UPDATE Auction a SET a.endTime = CURRENT_TIMESTAMP "
                        + "WHERE a.endTime < CURRENT_TIMESTAMP")
                .executeUpdate();
    }
}
