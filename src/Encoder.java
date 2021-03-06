import java.util.Base64;

public class Encoder {
	
	public static String encodeString(String encodedString) {
		try {
			//return new String(Base64.getDecoder().decode((encodedString.substring(0,encodedString.length()-1))));
			return Base64.getEncoder().encodeToString(encodedString.getBytes());
		}catch (Exception e) {
			System.err.println(e);
			return null;
		}		 
	}

}
