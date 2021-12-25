package ru.volkovd.JournalConsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volkovd.JournalConsumer.model.Event;
import ru.volkovd.JournalConsumer.repo.EventRepo;

@Service
public class EventService {
    final
    EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public void updateEvent(String text) {
        Event event = new Event(text);
        eventRepo.save(event);
    }
}
