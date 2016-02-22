package com.dbalota.show.services;

import java.util.Map;
import java.util.Set;

import com.dbalota.show.models.Auditorium;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface AuditoriumService {
    Map<String, Auditorium> getAuditoriums();

    // FIXME: 0.5% never used
    int getSeatsNumber(String auditoriumName);

    Set<Integer> getVipSeats(String auditoriumName);
}
