package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pobj.util.Chrono;

public class WordCount {
	
	/**
	 * @param ms
	 * @throws IOException
	 */
	public static void wordcount(MultiSet<String> ms) {		
		//Chargement du fichier ligne par ligne et découpage en mots
		String file = "data/WarAndPeace.txt"; 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line; 
			while ((line = br.readLine())!=null) { 
				for (String word : line.split("\\P{L}+")) { 
					if (word.equals("")) continue; // ignore les mots vides 6
					ms.add(word,1); //traitement à faire pour le mot word
				}
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Extraction de la liste des éléments
		List<String> liste = ms.elements();
		
		// Tri décroissant de la liste
		Comparator<String> comp =new WordComparator<String>(ms);
		Collections.sort(liste, comp.reversed());

		// Affichage des 10 premières valeurs
		System.out.println("Mots les plus fréquents : ");
		for (int i=0; i<10; i++) {
			String mot = liste.get(i);
			System.out.println("- " + mot + " : " + ms.count(mot) + " occurences");
			  	i++;
		}
	
	}
	
	
	/* --- MAIN --- */  
	public static <T> void main(String[] args) throws IOException {
		MultiSet<String> h1 = new HashMultiSet<>();
		MultiSet<String> h2 = new NaiveMultiSet<>();

		System.out.println("1 - Test  avec HashMultiSet :");
		Chrono chronoHash = new Chrono(); 
		wordcount(h1);
		chronoHash.stop(); 	
		
		System.out.println("\n----------------------------\n");

		System.out.println("2 - Test avec NaiveMultiSet : ");
		Chrono chronoNaive = new Chrono(); 
		wordcount(h2);
		chronoNaive.stop(); 

	}

}