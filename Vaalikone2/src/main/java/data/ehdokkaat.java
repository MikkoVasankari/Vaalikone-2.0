package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ehdokkaat
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ehdokas_id;
    private String etunimi;
    private String kotipaikkakunta;
    private int ika;
    private String ammatti;
    private String kommentti;
    private int ehdokas_num;


	public ehdokkaat(int ehdokas_id, String etunimi, int ehdokas_num, String kotipaikkakunta, int ika, String ammatti, String kommentti) {
        // TODO Auto-generated constructor stub
        this.ehdokas_id=ehdokas_id;
        this.etunimi=etunimi;
        this.kotipaikkakunta=kotipaikkakunta;
        this.ika=ika;
        this.ammatti=ammatti;
        this.kommentti=kommentti;
        this.ehdokas_num=ehdokas_num;
    }

    public ehdokkaat() {
        // TODO Auto-generated constructor stub
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

	public int getEhdokas_num() {
		return ehdokas_num;
	}

	public void setEhdokas_num(int ehdokas_num) {
		this.ehdokas_num = ehdokas_num;
	}





}