package ru.volkovd.JournalConsumer;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

import ru.volkovd.JournalConsumer.repo.EventRepo;
import ru.volkovd.JournalConsumer.services.EventService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@SpringBootApplication
public class Consumer {

    final
    EventService eventService;

    public Consumer(EventService eventService) {
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

    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }
}