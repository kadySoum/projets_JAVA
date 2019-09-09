package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	private int m1;
	private int m2;
	private int c1;
	private int c2;
	
	/**
	 * Construit une CroixContrainte en initialisant ses attributs avec les valeurs passées en argument
	 * @param m1 indice du premier emplacement du croisement
	 * @param c1 indice de la case où a lieu ce croisement pour le premier emplacement
	 * @param m2 indice du second emplacement du croisement
	 * @param c2 indice de la case où a lieu ce croisement pour le second emplacement
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2){
		this.m1 = m1;
		this.m2 = m2;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public int reduce(GrillePotentiel grille) {
		Dictionnaire d1 = grille.getMotsPot().get(m1) ; //dictionnaire lié au premier emplacement
		EnsembleLettre l1 = d1.calculEnsemblePossible(c1);
		Dictionnaire d2 = grille.getMotsPot().get(m2) ; //dictionnaire lié au second emplacement
		EnsembleLettre l2 =d2.calculEnsemblePossible(c2);
		EnsembleLettre s = l1.intersection(l2);
		int cpt = 0; // compteur de mots filtrés
		if (l1.size() > s.size()) {
			cpt += d1.filtreParIndice(c1, s);
		}
		if (l2.size() > s.size()) {
			cpt += d2.filtreParIndice(c2, s);
		}
		return cpt;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof CroixContrainte))
			return false;
		CroixContrainte crx = (CroixContrainte) other;
		if (m1 != crx.m1 || c1 != crx.c1 || m2 != crx.m2 || c2 != crx.c2)
			return false;
		return true;
	}

}
