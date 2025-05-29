package com.tharindu.me.auctionSystem.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.*;

public class MessageReceiver {

    public void startListening() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = (Connection) factory.createConnection();
            connection.setClientID("ActiveMQ-ClientApp-1.1.0");
            connection.start();

            Session session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("activeMQTopic");

            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(
                                "Received message: " +
                                        message.getBody(String.class)
                        );
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            System.out.println("Receiver started and listening...");
            // NOTE: Do not block the servlet thread here.
            // This method should be called from a background thread.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
