package com.tharindu.me.auctionSystem.web.servlet;

import com.tharindu.me.auctionSystem.DTO.ProductDTO;
import com.tharindu.me.auctionSystem.ejb.Bean.MessageStore;
import com.tharindu.me.auctionSystem.ejb.Remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomePage extends HttpServlet {
    @EJB
    private ProductService productService;
    private MessageStore store;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();

            // Exact portable JNDI names from the server log
            String prodLookup = "java:global/AS-EAR/EJB-Module/ProductServiceBean!com.tharindu.me.auctionSystem.ejb.Remote.ProductService";
            productService = (ProductService) ctx.lookup(prodLookup);

            String storeLookup = "java:global/AS-EAR/EJB-Module/MessageStore";
            store = (MessageStore) ctx.lookup(storeLookup);

        } catch (NamingException e) {
            throw new ServletException("EJB lookup failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Get products and set as request attribute
        List<ProductDTO> products = productService.getAllProducts(); // get actual products
        System.out.println("Size of products: " + products.size());
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Products retrieved successfully.");
        }
        req.setAttribute("messages", store.getMessages());
        req.setAttribute("products", products);
        req.getRequestDispatcher("index.jsp").forward(req, resp);// set it for JSP access
    }

}

