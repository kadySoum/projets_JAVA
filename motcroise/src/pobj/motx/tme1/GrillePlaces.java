package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces{
	private Grille grille;
	private List<Emplacement> places;
	private int compteur; // Le nombre de mots horizontaux

	/**
	 * Explore la grille fournie et calcule les emplacements de mots
	 * @param grille la grille initiale a explorer
	 */
	public GrillePlaces(Grille grille){
		this.grille=grille.copy();
		this.places = new ArrayList<>();
		for (int i=0;i<this.grille.nbLig();i++){
			List<Case> liste=new ArrayList<>();
			liste=getLig(i);
			cherchePlaces(liste);	
		}
		compteur=places.size();
		for (int j=0;j<this.grille.nbCol();j++){
			cherchePlaces(getCol(j));
		}
	}


	/**
	 * @return liste d'emplacement
	 */
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	/**
	 * @return le nombre de mots horizontaux dans la grille
	 */
	public int getNbHorizontal(){
		return compteur;
	}
	
	@Override
	public String toString() {
		String s = ""; 
		for(Emplacement e: places) {
			s+=e.toString()+"\t";
		}
		return s; 
	}
	
	public String getGrilleString() {
		return grille.toString();
	}
	/**
	 * Construit la liste des cases constituant la ligne d'indice spécifiée
	 * @param lig numéro de ligne
	 * @return la liste des cases
	 */
	private List<Case> getLig (int lig){
		List<Case> n = new ArrayList<Case>();
		for (int i=0; i<grille.nbCol() ;i++) {
			n.add(grille.getCase(lig, i));
		}
		return n ;
	}
	
	/**
	 * Construit la liste des cases sur une colonne donnée
	 * @param col le numéro de colonne
	 * @return la liste construite
	 */
	private List<Case> getCol(int col){
		List<Case> n = new ArrayList<Case>();
		for (int i=0; i<grille.nbLig();i++) {
			n.add(grille.getCase(i,col));
		}
		return n;
	}
	
	/**
	 * Cherche les mots de la liste de cases fournie et ajoute les emplacements de ces mots aux places de la grille
	 * @param cases la liste initiale des cases à parcourir
	 */
	private void cherchePlaces(List<Case> cases){
		Emplacement e= new Emplacement();
		for (Case c: cases) {
			if (!c.isPleine()) {     
				e.addCase(c);
			} else {
				if (e.size()>=2) {
					places.add(e);
					e = new Emplacement();
				} else {
					e = new Emplacement();
				}
			}
		}
		if (e.size()>1) {
			places.add(e);
		}
	}
	
	/**
	 * Crée une copie de la GrillePlaces dans laquelle le mot à l'emplacement m est fixé avec la valeur de soluce 
	 * @param m indice dans la grille du mot à fixer
	 * @param soluce mot à affecter à l'emplacement m
	 * @return la nouvelle grille
	 */
	public GrillePlaces fixer(int m, String soluce) {
		Grille newGrille = grille.copy();
		Emplacement em = places.get(m);
		//modification des cases de la soluce :
		int n = em.size();
		for (int i=0; i<n; i++) {
			Case oldCase = em.getCase(i);
			Case newCase = newGrille.getCase(oldCase.getLig(), oldCase.getCol());
			newCase.setChar(soluce.charAt(i));
		}
		return new GrillePlaces(newGrille); 
		
	}
		
}
