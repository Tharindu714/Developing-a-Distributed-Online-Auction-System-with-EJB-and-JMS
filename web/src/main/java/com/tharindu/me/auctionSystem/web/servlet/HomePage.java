package com.tharindu.me.auctionSystem.web.servlet;

import com.tharindu.me.auctionSystem.DTO.ProductDTO;
import com.tharindu.me.auctionSystem.ejb.Remote.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomePage extends HttpServlet {
    @Resource
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            productService = (ProductService) ctx.lookup("java:global/AS-EAR/ejb/ProductServiceBean!com.tharindu.me.auctionSystem.ejb.Remote.ProductService");
        } catch (NamingException e) {
            throw new ServletException("EJB lookup failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> products = productService.getAllProducts(); // This should be ProductDTO
        req.setAttribute("products", products);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
