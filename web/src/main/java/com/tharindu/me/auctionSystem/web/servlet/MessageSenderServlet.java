package com.tharindu.me.auctionSystem.web.servlet;

import com.tharindu.me.auctionSystem.activemq.MessageSender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/startSender")
public class MessageSenderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // You can later read input from form here
        String message = "Hello, This is ActiveMQ Message Sender";
        new MessageSender().sendMessage(message);

        // Optionally redirect or confirm
        resp.sendRedirect("index.jsp");
    }
}

