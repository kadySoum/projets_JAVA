package pobj.tme5;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	
	MultiSet<String> ms = new HashMultiSet<String>();
	
	public static <T> void wordcount(MultiSet<String> ms) throws IOException {
		String file = "data/WarAndPeace.txt"; 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine())!=null) { 
			for (String word : line.split("\\P{L}+")) { 
				if (word.equals("")) continue; // ignore les mots vides 6
				ms.add(word,1);
			}
		}
		br.close(); 
		System.out.println(ms.toString());
	}
	
	public static <T> void main(String[] args) throws IOException, AssertionException {

		MultiSet<String> l = new NaiveMultiSet<>(); 
		MultiSet<String> b = new HashMultiSet<>(); 
		MultiSet<String> checked = new MultiSetDecorator<>(b); 
	
		MultiSet<String> checked2 = new MultiSetDecorator<>(l); 
	
		checked.add("y"); 
		
		MultiSet<String> h1 = new HashMultiSet<>();

		try {

			System.out.println("HashMultiSet : ");
			//Chrono chrono = new Chrono(); 
			wordcount(h1);
			//chrono.stop(); 
			//System.out.println(chrono);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
		   System.out.println(e.getMessage());
		}

	}

}