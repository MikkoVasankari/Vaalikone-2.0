package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class vastaukset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int ehdokas_id;
	@Id
	private int kysymys_id;
	
	private int vastaus;
	private String kommentti;
	private int ehdokas_num;
	
	
	public vastaukset() {

	}
	
	public vastaukset(int ehdokas_id,int vastaus) {
		this.ehdokas_id = ehdokas_id;
		this.vastaus = vastaus;
	}

	public vastaukset(int ehdokas_id, int kysymys_id, int vastaus, int ehdokas_num) {
		this.ehdokas_id = ehdokas_id;
		this.kysymys_id = kysymys_id;
		this.vastaus = vastaus;
		this.ehdokas_num = ehdokas_num;
	}

	public int getEhdokas_id() {
		return ehdokas_id;
	}

	public void setEhdokas_id(int ehdokas_id) {
		this.ehdokas_id = ehdokas_id;
	}

	public int getKysymys_id() {
		return kysymys_id;
	}

	public void setKysymys_id(int kysymys_id) {
		this.kysymys_id = kysymys_id;
	}

	public int getVastaus() {
		return vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public String getKommentti() {
		return kommentti;
	}

	public void setKommentti(String kommentti) {
		this.kommentti = kommentti;
	}

	public int getEhdokas_num() {
		return ehdokas_num;
	}

	public void setEhdokas_num(int ehdokas_num) {
		this.ehdokas_num = ehdokas_num;
	}

}

