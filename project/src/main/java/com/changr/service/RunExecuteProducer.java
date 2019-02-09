package com.changr.service;


import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.changr.model.FulfillProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class RunExecuteProducer {

    @Value("${queue.execute-queue.name}")
    String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(FulfillProject fulfill)
    {
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(fulfill);
            }
        });
    }
}
