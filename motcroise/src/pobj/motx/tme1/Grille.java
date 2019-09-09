package pobj.motx.tme1;

public class Grille {
	private Case[][] matrice;
	
	/** 
	 * Construit une nouvelle Grille de taille lig*col puis initialise chaque case de la grille par une nouvelle  Case vide
	 * @param hauteur hauteur de la grille
	 * @param largeur largeur de la grille
	 */
	public Grille(int hauteur, int largeur) {
		matrice = new Case[hauteur][largeur];
		for (int i=0; i<hauteur; i++) {
			for (int j=0; j<largeur; j++) {
				matrice[i][j] = new Case(i, j, ' ');
			}
		}
	}
	
	/** 
	 * @param lig
	 * @param col
	 * @return la case à l'emplacement Grille[lig][col]
	 */
	public Case getCase(int lig, int col) {
		return matrice[lig][col];
	}
	
	@Override
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	/**
	 * @return le nombre de lignes dans la grille
	 */
	public int nbLig() {
		return matrice.length;
	}
	
	/**
	 * @return la nombre de colonnes dans la grille
	 */
	public int nbCol() {
		return matrice[0].length;
	}
	
	/** 
	 * Construit une Grille par copie
	 * @return la copie construite
	 */
	public Grille copy() {
		Grille newGrille = new Grille(nbLig(), nbCol());
		for (int i=0; i<nbLig(); i++) {
			for (int j=0; j<nbCol(); j++) {
				newGrille.matrice[i][j] = new Case(i, j, getCase(i, j).getChar());
			}
		}
		return newGrille;
	}
	
	}
