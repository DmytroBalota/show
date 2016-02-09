package com.dbalota.show.services.impl;

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

	public Map<String, Auditorium> getAuditoriums() {
		return auditoriumDao.getAuditoriums();
	}

	public int getSeatsNumber(String auditoriumName) {
		return auditoriumDao.getAuditoriums().get(auditoriumName).getSeatsNumber();
	}

	public Set<Integer> getVipSeats(String auditoriumName) {
		return auditoriumDao.getAuditoriums().get(auditoriumName).getVipSeats();
	}

}
