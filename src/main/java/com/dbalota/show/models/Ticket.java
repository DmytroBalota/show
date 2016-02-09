package com.dbalota.show.models;

import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class Ticket {
    private double price;
    private Auditorium auditorium;
    private Date date;
    private int seat;

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", auditorium=" + auditorium +
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

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
