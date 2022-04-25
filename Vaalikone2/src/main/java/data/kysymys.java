package data;

public class kysymys {
		private int id;

		private String kysymys;
		
		public kysymys(String id, String kysymys) {
			this.kysymys = kysymys;
			setId(id);
		}
		
		public kysymys() {
			
		}
		
		public void setId(int id) {
			this.id = id;
		}
		public String getKysymys() {
			return kysymys;
		}

		public void setKysymys(String kysymys) {
			this.kysymys = kysymys;
		}

		public int getId() {
			return id;
		}

		public void setId(String id) {
			try {
				this.id = Integer.parseInt(id);
			}
			catch(NumberFormatException | NullPointerException e) {
				//Do nothing - the value of id won't be changed
			}
		}
		
}
