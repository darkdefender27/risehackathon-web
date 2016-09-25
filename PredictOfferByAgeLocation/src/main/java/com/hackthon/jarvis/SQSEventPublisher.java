package com.hackthon.jarvis;

import com.hackthon.jarvis.domain.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Admin on 12-06-2016.
 */
@Component
public class SQSEventPublisher {

    public static final String DESTINATION_NAME = "EmailQueue";

    @Autowired
    protected JmsTemplate defaultJmsTemplate;

    public void sendSQSEvent( AlertMessage message) throws IOException {
        defaultJmsTemplate.convertAndSend(DESTINATION_NAME,
                message.toJSON());
    }
}
