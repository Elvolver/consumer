package ru.volkovd.JournalConsumer.model;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.volkovd.JournalConsumer.services.EventService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;

@Component
public class Receiver {

    final
    EventService eventService;

    public Receiver(EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(destination = "journalQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void receiveQueue(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            System.out.println("Получено текстовое сообщение:" + ((TextMessage) message).getText());
        } else if (message instanceof ActiveMQMapMessage) {
            System.out.println("Получено сообщение:" + ((ActiveMQMapMessage) message).getContentMap());

            String text = ((ActiveMQMapMessage) message).getContentMap().get("title").toString();
            eventService.updateEvent(text);
        } else {
            System.out.println(message);
        }
    }
/*
    @JmsListener(destination = "journalTopic", containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveTopic(String message) {
        System.out.println("Получено сообщение по подписке: " + message);
    }
*/


}