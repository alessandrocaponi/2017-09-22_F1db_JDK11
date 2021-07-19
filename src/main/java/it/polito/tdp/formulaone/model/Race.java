package it.polito.tdp.formulaone.model;


public class Race {
	
	private int raceId ;
	
	
	private String name ;
	
	public Race(int raceId,String name) {
		super();
		this.raceId = raceId;
		this.name = name;
	}
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + raceId;
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
		Race other = (Race) obj;
		if (raceId != other.raceId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return name;
	}
	
	

}



