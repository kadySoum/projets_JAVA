package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "dictionnaire de " + size() + " mots";
		}
	}
	
	/**
	 * charge un dictionnaire depuis un fichier texte
	 * @param path chemin du fichier
	 * @return le dictionnaire chargé
	 */
	public static Dictionnaire loadDictionnaire(String path) {
		//chargera un dictionnaire depuis un fichier texte
		Dictionnaire d = new Dictionnaire();
		try(BufferedReader br =new	BufferedReader(new FileReader(path))) {
		
		for (String line = br.readLine() ; line !=null; line = br.readLine() ) {
			d.add(line);
		}
		
		}catch(IOException e) {
		// Problème d’accès au fich
			System.out.println("Erreur fichier");
		}
		return d; 
	}
	
	/**
	 * Filtre le dictionnaire en conservant les mots contenant le cacactère c à la place i
	 * @param c caractère
	 * @param i position du caractère
	 * @return nombre de mots supprimés
	 */
	public int filtreParLettre(char c,int i) {
		int cpt=0; 
		List<String> newL = new ArrayList<String>();
		for (String s : mots) {
			if (s.charAt(i)==c) {
				newL.add(s);
			}
			else {
				cpt++;
			}
		}
		mots = newL;
		return cpt;
	}
	
	/**
	 * Calcule l’ensemble de lettre possible à une position donnée 
	 * @param i position dans le mot
	 * @return l'EnembleLettre contenant possible à la position donnée
	 */
	public EnsembleLettre calculEnsemblePossible(int i) {
		EnsembleLettre li = new EnsembleLettre();
		for (String m : mots) {		// parcours des mots du dictionnaire
			if (i<m.length()) { // on s'assure que le mot contient plus de i lettres
				char c = m.charAt(i);
				if (!li.contains(c)) {
					li.add(c);
				}
			}
		}
		return li;
	}
	
	/**
	 *  Filtre le dictionnaire par rapport à un indice i et un EnsembleLettre possible
	 *  Teste pour chaque mot que lp contient bien la lettre d’indice i du mot.
	 * @param i indice dans le mot
	 * @param ensLet ensemble de lettres
	 * @return le nombre de mots supprimés
	 */
	public int filtreParIndice(int i, EnsembleLettre lp) {
		int cpt=0; 
		List<String> newMots = new ArrayList<String>();
		for (String s : mots) {
			if (lp.contains(s.charAt(i))) {
				newMots.add(s);
			}
			else {
				cpt++;
			}
		}
		mots = newMots;
		return cpt;
	}
}