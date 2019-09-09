package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("unused")
public class MultisetParserTest {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InvalidMultiSetFormat {
		
		MultiSetParser s = new MultiSetParser();
		try{
		MultiSet<String> m = s.parse("file");
		
		System.out.println(m.toString() );
		
		}catch(InvalidMultiSetFormat e){
			System.out.println(e.getMessage());
		}
		
	}

}
