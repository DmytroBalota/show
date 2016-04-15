package com.dbalota.show.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class Ticket implements Serializable{
    private int id;
    private double price;
    private String auditoriumName;
    private Date date;
    private int seat;
    private int eventId;
    private int userId;
    private Auditorium auditorium;
    private Event event;

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", auditorium=" + auditoriumName +
                ", date=" + date +
                ", seat=" + seat +
                '}';
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
