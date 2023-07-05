import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashPassword;
	}

	public String[][] getProductTableData(int campi) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
		int lines = 0;
		while (br.readLine() != null) lines++;
		String data[][] = new String[lines][campi];
		br.close();
		br = new BufferedReader(new FileReader("./database/db_product.txt"));
		String s = "";
		int i = 0;
		while((s = br.readLine()) != null){
			data[i] = s.split(",");
			i++;
		}
		br.close();

		return data;
	}
	
	public String[][] getUsersTableData(int campi) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
		int lines = 0;
		while (br.readLine() != null) lines++;					
		String userdata[][] = new String[lines][campi];
		br.close();
		br = new BufferedReader(new FileReader("./database/db_users.txt"));
		String s = "";
		int i = 0;
		while((s = br.readLine()) != null){
			userdata[i] = s.split(",");
			i++;
		}
		br.close();
		return userdata;
	}
	
	public String getIap(BufferedReader br,int indice) {
		//se il db è di tipo prodotto l'indice è 1, per le richieste nuovo prodotto invece è 0
		try {
			String s="";
			int index =0;
			int maxIap=0;
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				index = split[indice].indexOf("D");
				String substring = split[indice].substring(index+1);
				index= Integer.valueOf(substring);
				if (index>maxIap) {
					maxIap=index;
				}
			}
			maxIap++;
			String IAP = String.valueOf(maxIap);
			if(IAP.length() == 1) {
				IAP = "CITD00" + IAP; 
			}else if(IAP.length() == 2) {
				IAP = "CITD0" + IAP; 
			}else {
				IAP = "CITD0" + IAP; 
			}
			return IAP;
		}catch(IOException e) {
			System.out.println(e.getMessage());
			return null;

		}
	}
	

	
	
}
