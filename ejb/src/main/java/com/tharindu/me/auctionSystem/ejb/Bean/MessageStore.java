package com.tharindu.me.auctionSystem.ejb.Bean;

import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MessageStore {

    private final List<String> messages = new ArrayList<>();

    @Lock(LockType.WRITE)
    public void addMessage(String msg) {
        messages.add(msg);
    }

    @Lock(LockType.READ)
    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
