package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity  //Annotoitu
public class ehdokkaat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ehdokas_id;
    private String etunimi;
    private String ehdokas_num;

    private String kotipaikkakunta;
    private int ika;
    private String ammatti;
    private String kommentti;

    private String kuva;
   
	public ehdokkaat(String ehdokas_id, String etunimi, String ehdokas_num, String kotipaikkakunta, int ika, String ammatti, String kommentti, String kuva) {
        // TODO Auto-generated constructor stub
        setEhdokas_Id(ehdokas_id);
        this.etunimi=etunimi;
        this.kotipaikkakunta=kotipaikkakunta;
        this.ika=ika;
        this.ammatti=ammatti;
        this.kommentti=kommentti;
        this.kuva=kuva;
        this.setEhdokas_num(ehdokas_num);
    }
    public String getKuva() {
		return kuva;
	}
	public void setKuva(String kuva) {
		this.kuva = kuva;
	}
	public ehdokkaat(int id ) {
		this.ehdokas_id = id;
		
	}
    public ehdokkaat() {
        // TODO Auto-generated constructor stub
    }
    public int getEhdokas_Id() {
        return ehdokas_id;
    }
    public void setEhdokas_Id(int id) {
        this.ehdokas_id = id;
    }
    public void setEhdokas_Id(String id) {
        try {
            this.ehdokas_id = Integer.parseInt(id);
        }
        catch(NumberFormatException | NullPointerException e) {
            //Do nothing - the value of id won't be changed
        }
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
    public String getEhdokas_num() {
        return ehdokas_num;
    }
    public void setEhdokas_num(String ehdokas_num) {
        this.ehdokas_num = ehdokas_num;
    }


}