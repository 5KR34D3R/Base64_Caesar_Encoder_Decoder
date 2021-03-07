import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Encoder {
	private static char [] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public static String encodeString(String encodedString) {
		try {			
			return Base64.getEncoder().encodeToString(encodedString.getBytes());
		}catch (Exception e) {
			System.err.println(e);
			return null;
		}		 
	}
	public static List<String> ceasarEncoder(String decodedString) {	
		decodedString = decodedString.toLowerCase();
		List<String> results = new ArrayList<String>();		
		String currentResult = "";		
		
		for(int offset = 1; offset <= alphabet.length; offset ++) {		
			for(int e = 0; e < decodedString.length(); e++) {				
				for(int current = 0; current < alphabet.length; current++) {							
					if(Character.valueOf(decodedString.charAt(e)).equals(alphabet[current])) {						
						if((current+offset) >= 26) {							
							currentResult += String.valueOf(alphabet[(current+offset-26)]);							
						}else {							
							currentResult += String.valueOf(alphabet[(current+offset)]);														
						}	
						break;
					}
				}				
			}
			results.add(currentResult);
			currentResult = "";
		}		
		return results;
	}

}
