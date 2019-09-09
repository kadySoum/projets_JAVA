package pobj.motx.tme1;
import java.util.List;
import java.util.ArrayList;

public class Emplacement {
	private List<Case> lettres = new ArrayList<Case>();
	
	@Override
	public String toString() {
		String s = "";
		for (Case lettre: lettres){
			s+= lettre.getChar();
			}
		return s;
	}
	
	/**
	 * @return la taille de l'emplacement
	 */
	public int size() {
		int cpt=0;
		for (Case c: lettres) {
			if(!(c.isPleine())){
				cpt++;
			}
		}return cpt;
		//return lettres.size();
	}
	
	/**
	 * Rajoute une case à l'emplacement
	 * @param c case à ajouter
	 */
	public void addCase(Case c) {
		lettres.add(c);
	}
	
	/**
	 * @param i l'indice de la case dans l'emplacement
	 * @return la case à l'indice i dans la liste de cases
	 */
	public Case getCase(int i) {
		return lettres.get(i);
	}
	
	/**
	 * tme3
	 * 
	 */
	public boolean hasCaseVide() {
		for(Case c : lettres) {
			if (c.isVide()) {
				return true;
			}
		}
		return false;
	}
}
