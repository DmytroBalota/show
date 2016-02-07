package com.dbalota.show.models;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class Auditorium {
	private String name;
	private int seatsNumber;
	private int vipSeatsNumber;

		
	@Override
	public String toString() {
		return "Auditorium [name=" + name + ", seatsNumber=" + seatsNumber + ", vipSeatsNumber=" + vipSeatsNumber + "]";
	}

	public Auditorium(String name, int seatsNumber, int vipSeatsNumber) {
		super();
		this.name = name;
		this.seatsNumber = seatsNumber;
		this.vipSeatsNumber = vipSeatsNumber;
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

	public int getVipSeatsNumber() {
		return vipSeatsNumber;
	}

	public void setVipSeatsNumber(int vipSeatsNumber) {
		this.vipSeatsNumber = vipSeatsNumber;
	}
}
