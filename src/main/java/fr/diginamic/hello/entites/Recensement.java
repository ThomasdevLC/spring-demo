package fr.diginamic.hello.entites;

import java.util.ArrayList;
import java.util.List;


public class Recensement {
		
		/** villes : List<Ville>*/
		private List<Ville> villes  = new ArrayList<>();
		

		/** Getter
		 * @return the villes
		 */
		public List<Ville> getVilles() {
			return villes;
		}

		/** Setter
		 * @param villes the villes to set
		 */
		public void setVilles(List<Ville> villes) {
			this.villes = villes;
		}
}
