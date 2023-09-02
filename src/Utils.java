import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
	
	public String getIapProduct(List<Prodotto> lista) {
		int index =0;
		int maxIap=0;
		for (Prodotto prodotto : lista) {
			index = prodotto.getIAP().indexOf("D");
			String substring = prodotto.getIAP().substring(index+1);
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
	}
	
	public String getIapProductRequest(List<RichiestaProdotto> lista) {
		int index =0;
		int maxIap=0;
		for (RichiestaProdotto richiesta : lista) {
			index = richiesta.getIap().indexOf("D");
			String substring = richiesta.getIap().substring(index+1);
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
	}

	public String giorniRimanenti(String data){
		
		// String [] splStrings =  scadenza.split("/");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
			Date dateScadenza = formatter.parse(data);
			Date today = new Date();	
			long diffInMillies = dateScadenza.getTime() - today.getTime();
			return "" + TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
