package com.tharindu.me.auctionSystem.web.servlet;

import com.tharindu.me.auctionSystem.ejb.Bean.JMSPublisherBean;
import com.tharindu.me.auctionSystem.ejb.Bean.MessageStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {

    private JMSPublisherBean publisher;
    private MessageStore store;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();

            publisher = (JMSPublisherBean) ctx.lookup(
                    "java:global/AS-EAR/EJB-Module/JMSPublisherBean"
            );
            store = (MessageStore) ctx.lookup(
                    "java:global/AS-EAR/EJB-Module/MessageStore"
            );

        } catch (NamingException e) {
            throw new ServletException("Failed to lookup EJBs in SendMessageServlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("messages");
        if (text != null && !text.isBlank()) {
            // 1) Publish to JMS
            publisher.publish(text);
            // 2) Also record it immediately in the store
            store.addMessage(text);
            System.out.println("Message sent: " + text);
        }
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
