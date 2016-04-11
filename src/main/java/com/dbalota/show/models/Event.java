package com.dbalota.show.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class Event implements Serializable {

    public Event(String name) {
        this.name = name;
    }

    public enum Rating {
        HIGH, MID, LOW;
    }

    public static  class DateLocation{
        private String date;
        private String location;

        public DateLocation(String date, String location) {
            this.date = date;
            this.location = location;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    private int id;
    private String name;
    private double price;
    /* in minutes */
    private int duration;
    private Rating rating;

    private List<DateLocation> datesLocations;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<DateLocation> getDatesLocations() {
        return datesLocations;
    }

    public void setDatesLocations(List<DateLocation> datesLocations) {
        this.datesLocations = datesLocations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [name=" + name + ", price=" + price + ", duration=" + duration + ", rating=" + rating
                + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
