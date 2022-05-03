package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ehdokasVastaukset {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		int ehdokas_id;
		int kysymys_id;
		String vastaus;
		String kommentti;
		int ehdokas_num;
		
		

		public ehdokasVastaukset(int ehdokas_id, int kysymys_id, String vastaus, String kommentti, int ehdokas_num) {
			this.ehdokas_id = ehdokas_id;
			this.kysymys_id = kysymys_id;
			this.vastaus = vastaus;
			this.kommentti = kommentti;
			this.ehdokas_num = ehdokas_num;
		}
		
		public ehdokasVastaukset() {
			
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

		public String getVastaus() {
			return vastaus;
		}

		public void setVastaus(String vastaus) {
			this.vastaus = vastaus;
		}


		public ehdokasVastaukset(int ehdokas_id, int kysymys_id, String vastaus) {
			this.ehdokas_id = ehdokas_id;
			this.kysymys_id = kysymys_id;
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
