package ru.volkovd.JournalConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Consumer extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }
}