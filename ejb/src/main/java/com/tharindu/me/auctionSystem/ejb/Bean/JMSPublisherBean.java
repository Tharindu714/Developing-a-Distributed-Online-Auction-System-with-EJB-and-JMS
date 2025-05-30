package com.tharindu.me.auctionSystem.ejb.Bean;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;

@Stateless
public class JMSPublisherBean {

    // Inject the ConnectionFactory, not JMSContext
    @Resource(lookup = "jms/myTopicConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/myTopic")
    private Topic topic;

    public void publish(String text) {
        // Create and close the JMSContext in a try-with-resources
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer()
                    .send(topic, text);
        }
    }
}
