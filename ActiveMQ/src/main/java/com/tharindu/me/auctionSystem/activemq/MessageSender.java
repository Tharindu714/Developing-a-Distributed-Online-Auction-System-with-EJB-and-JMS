package com.tharindu.me.auctionSystem.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.*;

public class MessageSender {

    public void sendMessage(String text) {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = (Connection) factory.createConnection();
            connection.setClientID("ActiveMQ-ClientApp-1.0.0");
            connection.start();

            Session session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("activeMQTopic");

            MessageProducer producer = session.createProducer(topic);
            TextMessage msg = session.createTextMessage(text);
            producer.send(msg);

            System.out.println("Message sent: " + text);

            producer.close();
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
