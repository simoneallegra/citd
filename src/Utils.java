import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	String hashPassword = "";
	
	public String db_user_path = "\"./database/db_users.txt\"";
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashPassword;
	}
	
}
