<%@ page import="com.tharindu.me.auctionSystem.DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Auction System</title>
    <style>
        /* Base reset */
        * { box-sizing: border-box; margin: 0; padding: 0; }

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
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
            overflow: hidden;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .assignment-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
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

<h1>Available Products</h1>
<ul>
    <%
        List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
        if (products != null) {
            for (ProductDTO product : products) {
    %>
    <li>
        <strong><%= product.getName() %></strong><br/>
        Description: <%= product.getDescription() %><br/>
        Starting Price: $<%= product.getStartingPrice() %>
    </li>
    <%
        }
    } else {
    %>
    <li>No products available.</li>
    <%
        }
    %>
</ul>

<footer>
    &copy; 2025 Auction System — All Rights Reserved
</footer>

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

</body>
</html>
