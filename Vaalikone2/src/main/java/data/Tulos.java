package data;

public class Tulos {
	
	int id;
	String vastaus;
	int ehdokasNum;
	
	public Tulos(int id, String vastaus) {
		this.id = id;
		this.vastaus = vastaus;
	}
	public Tulos(int id,int ehdokasNum, String ehdokkaanVastaus) {
		this.ehdokasNum = ehdokasNum;
		this.id = id;
		this.vastaus = ehdokkaanVastaus;
	}
	
	public Tulos() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVastaus() {
		return vastaus;
	}

	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}
	
	public int getEhdokasNum() {
		return ehdokasNum;
	}
	public void setEhdokasNum(int ehdokasNum) {
		this.ehdokasNum = ehdokasNum;
	}

}
