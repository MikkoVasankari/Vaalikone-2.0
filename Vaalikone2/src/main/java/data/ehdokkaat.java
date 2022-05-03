package data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ehdokkaat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int ehdokas_id;
	private String etunimi;
	private int ehdokas_num;
	private String kotipaikkakunta;
	private int ika;
	private String ammatti;
	private String kommentti;

	
	
	public ehdokkaat() {

	}
	
	public ehdokkaat(int ehdokas_id, String etunimi, int ehdokas_num, String kotipaikkakunta, int ika, String ammatti,
			String kommentti) {
		this.ehdokas_id = ehdokas_id;
		this.etunimi = etunimi;
		this.ehdokas_num = ehdokas_num;
		this.kotipaikkakunta = kotipaikkakunta;
		this.ika = ika;
		this.ammatti = ammatti;
		this.kommentti = kommentti;
	}

	public int getEhdokas_id() {
		return ehdokas_id;
	}

	public void setEhdokas_id(int ehdokas_id) {
		this.ehdokas_id = ehdokas_id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public int getEhdokas_num() {
		return ehdokas_num;
	}

	public void setEhdokas_num(int ehdokas_num) {
		this.ehdokas_num = ehdokas_num;
	}

	public String getKotipaikkakunta() {
		return kotipaikkakunta;
	}

	public void setKotipaikkakunta(String kotipaikkakunta) {
		this.kotipaikkakunta = kotipaikkakunta;
	}

	public int getIka() {
		return ika;
	}

	public void setIka(int ika) {
		this.ika = ika;
	}

	public String getAmmatti() {
		return ammatti;
	}

	public void setAmmatti(String ammatti) {
		this.ammatti = ammatti;
	}

	public String getKommentti() {
		return kommentti;
	}

	public void setKommentti(String kommentti) {
		this.kommentti = kommentti;
	}

}
