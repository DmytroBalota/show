package com.dbalota.show.dao;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Ticket;


import java.util.*;

/**
 * Created by dmytro_balota on 2/9/2016.
 */
public class BookingDao {

    private static Map<MultiKey, List<Ticket>> purchasedTicket = new HashMap<>();

    public List<Ticket> getPurchasedTickets(Auditorium auditorium, Date date){
        return purchasedTicket.get(new MultiKey(auditorium,date));
    }

    public void bookTicket(Ticket ticket) {
        MultiKey key = new MultiKey(ticket.getAuditorium(), ticket.getDate());

        if (purchasedTicket.containsKey(key)) {
            purchasedTicket.get(key).add(ticket);
        }else{
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);
            purchasedTicket.put(key, tickets);
        }
    }

    class MultiKey {
        private Auditorium auditorium;
        private Date date;

        public MultiKey(Auditorium auditorium, Date date) {
            this.auditorium = auditorium;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MultiKey multiKey = (MultiKey) o;

            if (!auditorium.equals(multiKey.auditorium)) return false;
            return date.equals(multiKey.date);

        }

        @Override
        public int hashCode() {
            int result = auditorium.hashCode();
            result = 31 * result + date.hashCode();
            return result;
        }
    }
}
