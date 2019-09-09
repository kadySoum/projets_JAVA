package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("unused")
public class MultiSetParser {
	
	@SuppressWarnings("resource")
	public static MultiSet<String> parse(String fileName) throws IOException, InvalidMultiSetFormat{
		MultiSet<String> m = new HashMultiSet<>();
		
		try{
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		String line; 

		while (((line = br.readLine())!=null)) { 
			if ( ! line.equals("")){
				if ( !line.contains(":"))
					throw new InvalidMultiSetFormat("Format invalide (:) ");
				
				String word [] = line.split(":"); 
				if ( word[1].contains(" ")){
					
					String m1 = word[1].trim();
					try{
						
					m.add(word[0].trim(),Integer.decode(m1));
					}catch(NumberFormatException e){
						throw new InvalidMultiSetFormat("Format invalide (Integer) ",e);
					}
				}else{
				try{
					m.add(word[0].trim(),Integer.decode(word[1].trim()));
					}catch(NumberFormatException e){
						throw new InvalidMultiSetFormat("Format invalide (Integer) ",e);
					}
					
				}
				
			}
		}
		br.close();
		}catch(IOException e){
			throw new InvalidMultiSetFormat("Format invalide (file) ",e);
		}
		return m;
	
	}
}
