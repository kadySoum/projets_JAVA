package pobj.motx.tme2;

import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

import java.util.ArrayList;

public class GrillePotentiel {
	private GrillePlaces grille; 
	private Dictionnaire dicoComplet; 
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;
	
	/**
	 * initialise les attributs aux valeurs données puis initialise le domaine des emplacements et les contraintes.
	 * @param grille grille de mots
	 * @param dicoComplet dictionnaire complet de la langue francaise
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille = grille;
		this.dicoComplet= dicoComplet;
		contraintes = new ArrayList<IContrainte>();
		motsPot= new ArrayList<Dictionnaire>();
		List<Emplacement> places = grille.getPlaces();
		
		// FILTRAGE
		for (Emplacement em: places) {
			Dictionnaire dico = dicoComplet.copy();
			int sizeEm = em.size();
			dico.filtreLongueur(sizeEm); //filtrage par longueur
			//filtrage en fonction des mots déjà placés :
			for (int i=0; i<sizeEm; i++) {
				Case c = em.getCase(i);
				if (!c.isVide() && !c.isPleine()) {
					dico.filtreParLettre(c.getChar(), i);
				}
			}
			motsPot.add(dico);
		}
		
		// DETECTION DES CROISEMENTS
		for (int m1=0; m1<grille.getNbHorizontal(); m1++) { //parcours des emplacements horizontaux
			Emplacement i = grille.getPlaces().get(m1);
			for (int m2 = grille.getNbHorizontal(); m2<grille.getPlaces().size() ; m2++) { //parcours des emplacements verticaux
				Emplacement j = grille.getPlaces().get(m2);
				for (int c1=0; c1<i.size(); c1++) { //parcours des cases du mot horizontal
					Case a = i.getCase(c1);
					for (int c2=0; c2<j.size(); c2++) { //parcours des cases du mot vertical
						Case b = j.getCase(c2);
						if (a.getLig() == b.getLig() && a.getCol() == b.getCol() && a.isVide()) { //détection d'un croisement + case non remplie
							contraintes.add(new CroixContrainte(m1, c1, m2, c2)); //ajout d'une nouvelle contrainte	
						}
					}
				}
			}
		}	
		propage();
	}
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> potentiel) {
		this.grille = grille;
		this.dicoComplet= dicoComplet;
		contraintes = new ArrayList<IContrainte>();
		motsPot= new ArrayList<Dictionnaire>();
		List<Emplacement> places = grille.getPlaces();
		
		// FILTRAGE
		for (int e=0; e<places.size(); e++) {
			Emplacement em = places.get(e);
			Dictionnaire dico = potentiel.get(e);
			int sizeEm = em.size();
			dico.filtreLongueur(sizeEm); //filtrage par longueur
			//filtrage en fonction des mots déjà placés :
			for (int i=0; i<sizeEm; i++) {
				Case c = em.getCase(i);
				if (!c.isVide() && !c.isPleine()) {
					dico.filtreParLettre(c.getChar(), i);
				}
			}
			motsPot.add(dico);
		}
		
		// DETECTION DES CROISEMENTS
		for (int m1=0; m1<grille.getNbHorizontal(); m1++) { //parcours des emplacements horizontaux
			Emplacement i = grille.getPlaces().get(m1);
			for (int m2 = grille.getNbHorizontal(); m2<grille.getPlaces().size() ; m2++) { //parcours des emplacements verticaux
				Emplacement j = grille.getPlaces().get(m2);
				for (int c1=0; c1<i.size(); c1++) { //parcours des cases du mot horizontal
					Case a = i.getCase(c1);
					for (int c2=0; c2<j.size(); c2++) { //parcours des cases du mot vertical
						Case b = j.getCase(c2);
						if (a.getLig() == b.getLig() && a.getCol() == b.getCol() && a.isVide()) { //détection d'un croisement + case non remplie
							contraintes.add(new CroixContrainte(m1, c1, m2, c2)); //ajout d'une nouvelle contrainte	
						}
					}
				}
			}
		}	
		propage();
	}
	
	/**
	 * @return vrai ssi au moins un emplacement a un domaine potentiel vide
	 */
	public boolean isDead() {
		for(Dictionnaire d: motsPot) {
			if(d.size() == 0) {
				return true; //dead
			}
		}
		return false;
	}
	
	/**
	 * @return l'ensemble des domaines potentiels liés aux emplacements de la grille
	 */
	public List<Dictionnaire> getMotsPot(){
		return motsPot;
	}
	
	/**
	 * Construit une nouvelle GrillePotentielle dans laquelle le mot m de la grille est fixé par la valeur de soluce
	 * @param m
	 * @param soluce
	 * @return la nouvelle GrillePotentielle
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		GrillePlaces newGrille = grille.fixer(m, soluce);
		Dictionnaire newDicoComplet = dicoComplet.copy();
		return new GrillePotentiel(newGrille, newDicoComplet, motsPot);
	}

	/**
	 * @return la liste des contraintes
	 */
	public List<IContrainte> getContraintes(){
		return contraintes; 
	}
	
	private boolean propage() {
		int cpt = 0;
		while(true) {
			cpt = 0;
			for (IContrainte contrainte : contraintes) {
				cpt += contrainte.reduce(this);
			}
			if (isDead()) { // mot croisé irréalisable
				return false;
			}
			if (cpt == 0) { // stabilité
				return true;
			}
		}
	}
	public String toString() {
		return grille.getGrilleString();
	}
	/**
	 * Determine si l'emplacement i de la grille possède une case vide
	 * @param i indice de l'emplacement
	 * @return true si cet emplacement a une case vide
	 */
	public boolean hasCaseVide(int i) { 
		return grille.getPlaces().get(i).hasCaseVide();
	}
}