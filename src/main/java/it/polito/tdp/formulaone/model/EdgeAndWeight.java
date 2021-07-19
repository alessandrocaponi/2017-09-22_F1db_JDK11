package it.polito.tdp.formulaone.model;

public class EdgeAndWeight implements Comparable<EdgeAndWeight>{

	int raceId1;
	int raceId2;
	Integer peso;
	public EdgeAndWeight(int raceId1, int raceId2, Integer peso) {
		super();
		this.raceId1 = raceId1;
		this.raceId2 = raceId2;
		this.peso = peso;
	}
	public int getRaceId1() {
		return raceId1;
	}
	public void setRaceId1(int raceId1) {
		this.raceId1 = raceId1;
	}
	public int getRaceId2() {
		return raceId2;
	}
	public void setRaceId2(int raceId2) {
		this.raceId2 = raceId2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(EdgeAndWeight other) {
		
		return other.getPeso().compareTo(this.getPeso());
	}
	
}
