<%@ page import="com.tharindu.me.auctionSystem.DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Auction System</title>
    <style>
        /* Base reset */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: #f5f7fa;
            color: #333;
            line-height: 1.6;
            padding: 20px;
        }

        header {
            text-align: center;
            margin-bottom: 30px;
        }

        header h1 {
            font-size: 2.5rem;
            color: #2c3e50;
        }

        header p {
            color: #7f8c8d;
        }

        /* Assignment area container */
        .assignment-area {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
        }

        /* Single assignment card */
        .assignment-card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
            overflow: hidden;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .assignment-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        }

        .assignment-card header {
            background: #2980b9;
            color: #fff;
            padding: 15px;
            font-size: 1.25rem;
        }

        .assignment-card .content {
            padding: 15px;
        }

        .assignment-card .content p {
            margin-bottom: 10px;
        }

        .assignment-card .content .btn {
            display: inline-block;
            padding: 8px 12px;
            border-radius: 4px;
            background: #27ae60;
            color: #fff;
            text-decoration: none;
            font-size: 0.9rem;
            transition: background 0.2s;
        }

        .assignment-card .content .btn:hover {
            background: #229954;
        }

        /* Footer */
        footer {
            text-align: center;
            margin-top: 40px;
            color: #7f8c8d;
            font-size: 0.9rem;
        }

        .jms-btn {
            padding: 12px 24px;
            font-size: 1rem;
            border: none;
            border-radius: 5px;
            margin: 10px;
            cursor: pointer;
            color: #fff;
            transition: background 0.3s ease, transform 0.2s ease;
        }

        .sender-btn {
            background: #3498db;
        }

        .sender-btn:hover {
            background: #2980b9;
            transform: scale(1.05);
        }

        .receiver-btn {
            background: #8e44ad;
        }

        .receiver-btn:hover {
            background: #732d91;
            transform: scale(1.05);
        }

        .product-grid {
            padding: 30px;
            background-color: #f9f9f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            text-align: center;
        }

        .section-title {
            font-size: 2em;
            margin-bottom: 20px;
            color: #333;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            max-width: 1000px;
            margin: 0 auto;
        }

        .product-card {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: left;
            transition: transform 0.2s ease-in-out;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .product-card h3 {
            color: #0077cc;
            margin-top: 0;
        }

        .product-card p {
            margin: 8px 0;
            color: #555;
        }

    </style>
</head>
<body>
<header>
    <h1>Welcome to the Auction Dashboard</h1>
    <p>Your one-stop place to manage assignments & bids</p>
</header>

<section class="assignment-area">
    <!-- Assignment Card 1 -->
    <div class="assignment-card">
        <header>Setup & GitHub Workflow</header>
        <div class="content">
            <p><strong>Status:</strong> In Progress</p>
            <p><strong>Due:</strong> 2025-06-01</p>
            <a href="setup-guide.jsp" class="btn">View Details</a>
        </div>
    </div>

    <!-- Assignment Card 2 -->
    <div class="assignment-card">
        <header>EJB & JMS Implementation</header>
        <div class="content">
            <p><strong>Status:</strong> Done</p>
            <p><strong>Due:</strong> 2025-05-25</p>
            <a href="implementation.jsp" class="btn">View Details</a>
        </div>
    </div>

    <!-- Assignment Card 3 -->
    <div class="assignment-card">
        <header>Testing Strategy & Report</header>
        <div class="content">
            <p><strong>Status:</strong> Pending Review</p>
            <p><strong>Due:</strong> 2025-05-29</p>
            <a href="testing-report.jsp" class="btn">View Details</a>
        </div>
    </div>

    <!-- Assignment Card 4 -->
    <div class="assignment-card">
        <header>Technical Report</header>
        <div class="content">
            <p><strong>Status:</strong> Completed</p>
            <p><strong>Due:</strong> 2025-06-01</p>
            <a href="tech-report.jsp" class="btn">View Details</a>
        </div>
    </div>
</section>

<section class="product-grid">
    <h1 class="section-title">Available Products</h1>

    <div class="grid-container">
            <%
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
%>
        <div class="grid-container">
            <% if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
            %>
            <div class="product-card">
                <h3><%= product.getName() %>
                </h3>
                <p><strong>Description:</strong> <%= product.getDescription() %>
                </p>
                <p><strong>Starting Price:</strong> Rs. <%= product.getStartingPrice() %>
                </p>
            </div>
            <% }
            } else { %>
            <p>No products available.</p>
            <% } %>
        </div>

</section>

<section style="margin-top: 40px; text-align: center;">
    <h2>JMS Chat</h2>

    <form action="sendMessage" method="post" style="margin-bottom:20px;">
        <input
                type="text"
                name="message"
                placeholder="Type your message…"
                style="width:300px; padding:8px;"
                required
        />
        <button type="submit" class="jms-btn sender-btn">✉️ Send</button>
    </form>

    <div id="messageList" style="max-width:400px; margin:0 auto; text-align:left;">
        <c:forEach var="msg" items="${messages}">
            <div style="padding:6px; border-bottom:1px solid #ddd;">
                    ${msg}
            </div>
        </c:forEach>
        <c:if test="${empty messages}">
            <div>No messages yet.</div>
        </c:if>
    </div>
</section>

<section style="margin-top: 40px; text-align: center;">
    <h2>JMS Actions</h2>
    <div style="margin-top: 20px;">
        <form action="startSender" method="post" style="display:inline;">
            <button class="jms-btn sender-btn" type="submit">🚀 Start Message Sender</button>
        </form>
        <form action="startReceiver" method="post" style="display:inline;">
            <button class="jms-btn receiver-btn" type="submit">📥 Start Message Receiver</button>
        </form>
    </div>
</section>
<footer>
    &copy; 2025 Auction System — All Rights Reserved
</footer>
</body>
</html>
