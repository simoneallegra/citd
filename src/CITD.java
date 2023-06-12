import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class CITD {

	private Prodotto[] prodotto;

	private CITDamministratore cITDamministratore;

	private Prodotto[] listaProdotto;

	private Impiegato impiegato;

	private Utente[] listaUtenti;
	
	private Utils utility;

	public CITD(){
		utility = new Utils();
	}
	
	public Utente Login(String matricola, String password) {
		
		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[2];
					data = s.split(",");
					
					
					if((data[0].equals(matricola)) && (data[1].equals(utility.getEncryptPassword(password)))){
						System.out.println("login done");
						return new Utente(matricola);
					}else {
						System.out.println("wrong credentials");
					}
					
				}
			
				br.close();
		}catch(Exception e) {}
		
		return null;
	}

	public Prodotto visualizzaProdotto(char[] nome) {
		return null;
	}

	public void modificaProdotto(Prodotto prodotto) {

	}

	public void eliminaProdotto(Prodotto prodotto) {

	}

	public void aggiungiProdotto(Prodotto prodotto) {

	}

}
