import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class CITD{

	
	private Utils utility;
	private Utente utente;
	private Prodotto prod;

	public CITD(){
		utility = new Utils();
		utente = new Utente();
		prod = new Prodotto();
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
						return new Utente(data[0], data[1]);
					}else {
						System.out.println("wrong credentials");
					}
					
				}
			
				br.close();
		}catch(Exception e) {}
		
		return null;
	}
	
	public Utente getDetailsUtente(String matricola) {
		return utente.get(matricola);
	}
	
	public void inserisciNuovoUtente (Utente user) {
		System.out.println(user.password);
		utente.set(user);
	}
	
	public void updateUtente(String oldMatricola, Utente user) {
		utente.edit(oldMatricola, user);
	}
	
	public void eliminaUtente(String matricola) {
		utente.remove(new Utente(matricola));
	}
	
	
	public Prodotto visualizzaProdotto(String nome) {
		try {
			prod = prod.visualizza(nome);
			if(prod == null) {
				return null;
			}else {
					return prod;
			}
		}catch(Exception e) {
			return null;
			}
	}

	public Prodotto modificaProdotto(String prodotto) {
		prod = prod.edit(prodotto);
			return prod;
	}

	public String eliminaProdotto(String numeroseriale) {
		try {
			String product = prod.destroy(numeroseriale);
			return product;
		}catch(Exception e) {
			return null;
		}
	}

	public String aggiungiProdotto(String nome, String serial_number, String iap, String tipo, String marca) {
		try {
			String product = prod.aggiungiProdotto(nome, serial_number, iap, tipo, marca);
			return product;
		}catch(Exception e) {
			return null;
		}
	}


	

}
