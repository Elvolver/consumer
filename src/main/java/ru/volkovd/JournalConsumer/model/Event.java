package ru.volkovd.JournalConsumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "events")
public class Event {

    @Id
    private String id;
    private String text;
    private Date date;

    public Event() {
    }

    public Event(String id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public Event(String text) {
        this.text = text;
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}