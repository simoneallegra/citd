import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Utils {

	String hashPassword = "";
	
	public Utils() {
		
	}
	
	public String getEncryptPassword(String password){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			BigInteger number = new BigInteger(1, hash);
			StringBuilder hexString = new StringBuilder(number.toString(16));
			while (hexString.length() < 64)
	        {
	            hexString.insert(0, '0');
	        }
			hashPassword = hexString.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashPassword;
	}
	
	public String[][] listToMatrixString(List list){
		String[][] data = new String[list.size()][0];
		for(int i = 0 ; i < list.size() ; i++){
			data[i] = list.get(i).toString().split(",");
		}
		return data;
	}
	

	
	
}
