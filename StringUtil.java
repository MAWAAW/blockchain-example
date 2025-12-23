package noobchain;
import java.security.MessageDigest;

import com.google.gson.GsonBuilder;

public class StringUtil {
	
	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException("Error applying SHA-256: " + e);
		}
	}
	
	// Converts an object to pretty JSON
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	// Returns a string corresponding to the target difficulty (e.g., "00000")
	public static String getDifficultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
}
