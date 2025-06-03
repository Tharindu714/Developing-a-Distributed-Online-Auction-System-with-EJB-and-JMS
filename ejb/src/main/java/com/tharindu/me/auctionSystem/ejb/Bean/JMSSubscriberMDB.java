package com.tharindu.me.auctionSystem.ejb.Bean;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.inject.Inject;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",           propertyValue = "jms/myTopic"),
        @ActivationConfigProperty(propertyName = "destinationType",             propertyValue = "jakarta.jms.Topic"),
        @ActivationConfigProperty(propertyName = "connectionFactoryLookup",     propertyValue = "jms/myTopicConnectionFactory")
})
public class JMSSubscriberMDB implements MessageListener {

    @Inject
    private MessageStore store;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage tm) {
                store.addMessage(tm.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


