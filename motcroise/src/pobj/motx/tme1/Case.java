package pobj.motx.tme1;

public class Case {
	private int lig;
	private int col;
	private char c;
	
	/**
	 * Construit une case en initialisant les attributs au valeurs données
	 * @param lig numéro de ligne
	 * @param col numéro de colonne
	 * @param c caractère dans la case. ' ' pour case blanche, '*' pour case noire
	 */
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.c = c;
		this.col = col;
	}

	/**
	 * @return le numéro de ligne
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * @return le numéro de colonne
	 */
	public int getCol() {
		return col;
	}


	/**
	 * @return le caractère contenu dans la case
	 */
	public char getChar() {
		return c;
	}

	/**
	 * Place le caractère c dans la case
	 * @param c le caractère a placer
	 */
	public void setChar(char c) {
		this.c = c;
	}
	
	/**
	 * @return true ssi la case est blanche
	 */
	public boolean isVide() {
		return c == ' ';
	}
	
	/**
	 * @return true ssi la case est noire
	 */
	public boolean isPleine() {
		return c == '*';
	}
}
