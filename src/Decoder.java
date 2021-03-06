import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Decoder {
	private static char [] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	
	public static String decodeString(String encodedString) {
		try {
			// If length is smaller than 10 cause of different Alignment of =
			if(encodedString.length() < 10) {
				return new String(Base64.getDecoder().decode((encodedString.substring(0,encodedString.length()))));
			}else {
				return new String(Base64.getDecoder().decode((encodedString.substring(0,encodedString.length()-1))));
			}			
		}catch (Exception e) {			
			return null;
		}
		 
	}
	
	public static List<String> ceasarDecoder(String encodedString) {	
		encodedString = encodedString.toLowerCase();
		List<String> results = new ArrayList<String>();
		String currentResult = "";		
		for(int offset = 1; offset <= alphabet.length; offset ++) {		
			for(int e = 0; e < encodedString.length(); e++) {				
				for(int current = 0; current < alphabet.length; current++) {							
					if(Character.valueOf(encodedString.charAt(e)).equals(alphabet[current])) {						
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
	
	public static List<String> replaceElements(List<String> decodedList){	
		List<String> results = new ArrayList<>();
		results.add(0,"----------------------------------URLS----------------------------------");
		results.add(1,"----------------------------------------------------------------------------");
		for(String s: decodedList) {
			s = s.replace("https", "https");		
			s = s.replace("doppelpunkt", ":");
			s = s.replace("slash", "/");
			s = s.replace("minus", "-");
			s = s.replace("punkt", ".");
			
			if(s.contains("https://")) {
				results.add(1,s);
			}else {
				results.add(s);
			}
			
		}		
		return results;
	}	

}
