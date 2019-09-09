package pobj.motx.tme2;

public interface IContrainte {
	
	/**
	 * modifie la grille passée en argument, et rend le nombre total de mots filtrés par son action
	 * @param grille grille à filtrer
	 * @return le nombre de mots filtrés
	 */
	public abstract int reduce(GrillePotentiel grille); // définie comme abstraite car son comportement dépendra de chaque contrainte
		
}
