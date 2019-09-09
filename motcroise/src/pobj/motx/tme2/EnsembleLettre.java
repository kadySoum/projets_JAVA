package pobj.motx.tme2;

import java.util.List;
import java.util.ArrayList;


public class EnsembleLettre {
	private List<Character> listLettres;
	
	/**
	 * Constuit un nouvel EnsembleLettre en initialisant l'attribut listLettres comme une liste vide de caractères
	 */
	public EnsembleLettre() {
		listLettres = new ArrayList<Character>();
	}
	
	public EnsembleLettre(List<Character> listLettres) {
		this();
		if (listLettres  != null) {
			this.listLettres = listLettres;
		}
	}
	
	/**
	 * Ajoute un caractère à la liste de lettres
	 * @param c caractère à ajouter
	 */
	public void add(char c) {
		listLettres.add(c);
	}
	
	/**
	 * @return la taille de l'ensemble
	 */
	public int size() {
		return listLettres.size();
	}
	
	/**
	 * Effectue l'intersection entre deux ensembles de lettres
	 * @param e ensemble de lettre avec lequel on souhaite réaliser l'intersection
	 * @return l'intersection entre les deux listes
	 */
	public EnsembleLettre intersection(EnsembleLettre e) {
		List<Character> listLettresCopy = new ArrayList<Character>();
		for (Character c : listLettres) {
			listLettresCopy.add(c);
		}
		listLettresCopy.retainAll(e.listLettres);
		return new EnsembleLettre(listLettresCopy);
	}
	
	/**
	 * @param c caractère à tester
	 * @return true si c est dans l'ensmble de lettres
	 */
	public boolean contains(char c) {
		return listLettres.contains(c);
	}
}
