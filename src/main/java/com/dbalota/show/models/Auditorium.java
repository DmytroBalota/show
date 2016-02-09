package com.dbalota.show.models;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class Auditorium {
    private String name;
    private int seatsNumber;
    private Set<Integer> vipSeats;

    @Override
    public String toString() {
        return "Auditorium{" +
                "name='" + name + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", vipSeats=" + vipSeats +
                '}';
    }

    public Auditorium(String name, int seatsNumber, Set<Integer> vipSeats) {
        super();
        this.name = name;
        this.seatsNumber = seatsNumber;
        this.vipSeats = vipSeats;
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
        Auditorium other = (Auditorium) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Set<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }
}
