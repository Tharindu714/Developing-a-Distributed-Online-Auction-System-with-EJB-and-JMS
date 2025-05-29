package com.tharindu.me.auctionSystem.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.tharindu.me.auctionSystem.activemq.MessageReceiver;

import java.io.IOException;

@WebServlet("/startReceiver")
public class MessageReceiverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Thread receiverThread = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (receiverThread == null || !receiverThread.isAlive()) {
            receiverThread = new Thread(() -> {
                new MessageReceiver().startListening();
            });
            receiverThread.start();
        }

        resp.sendRedirect("index.jsp"); // or show a confirmation
    }
}


