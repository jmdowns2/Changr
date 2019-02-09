package com.changr.config;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SqsConfig {
    @Value("${queue.execute-queue.endpoint}")
    private String endpoint;
    @Value("${queue.execute-queue.name}")
    private String queueName;

    @Bean
    public JmsTemplate createJMSTemplate() {
        SQSConnectionFactory sqsConnectionFactory = SQSConnectionFactory.builder()
                .withAWSCredentialsProvider(new EnvironmentVariableCredentialsProvider())
                .withEndpoint(endpoint)
                .withNumberOfMessagesToPrefetch(10).build();
        JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory);
        jmsTemplate.setDefaultDestinationName(queueName);
        jmsTemplate.setDeliveryPersistent(false);
        return jmsTemplate;
    }

}