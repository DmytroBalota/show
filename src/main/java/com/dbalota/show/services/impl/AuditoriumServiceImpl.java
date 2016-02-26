package com.dbalota.show.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dbalota.show.dao.AuditoriumDao;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.services.AuditoriumService;

public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumDao auditoriumDao;

    AuditoriumServiceImpl(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriumDao.getAuditoriums();
    }

    public Auditorium getAuditorium(String name) {
        return auditoriumDao.getAuditoriumByName(name);
    }

    public int getSeatsNumber(String auditoriumName) {
        return auditoriumDao.getAuditoriumByName(auditoriumName).getSeatsNumber();
    }

    public Set<Integer> getVipSeats(String auditoriumName) {
        return auditoriumDao.getAuditoriumByName(auditoriumName).getVipSeats();
    }

    @Override
    public void addAuditorium(Auditorium auditorium) {
        auditoriumDao.addAuditorium(auditorium);
    }
}
