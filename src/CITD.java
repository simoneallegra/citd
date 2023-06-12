import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class CITD implements CITDamministratore{

	private Prodotto[] prodotto;


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
	
	@Override
	public void inserisciNuovoUtente (Utente utente, String password) {
		System.out.println("provaprova");
		try {
			String newField = utente.matricola + "," + utility.getEncryptPassword(password);
			Files.write(Paths.get("./database/db_users.txt"), newField.getBytes(), StandardOpenOption.APPEND);
			
		}catch(Exception e) {}
		
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


	@Override
	public void eliminaProdotto(int IAPprodotto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminaUtente(Utente utente) {
		// TODO Auto-generated method stub
		
	}

}
