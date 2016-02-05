package com.dbalota.show.services;

import com.dbalota.show.models.Auditorium;

import java.util.List;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface AuditoriumService {
    List<Auditorium> getAuditoriums();

    int getSeatsNumber();

    int getVipSeats();
}
