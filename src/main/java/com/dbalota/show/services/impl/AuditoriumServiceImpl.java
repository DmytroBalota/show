package com.dbalota.show.services.impl;

import java.util.Map;

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

	public int getVipSeats(String auditoriumName) {
		return auditoriumDao.getAuditoriums().get(auditoriumName).getVipSeatsNumber();
	}

}
