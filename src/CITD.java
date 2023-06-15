import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class CITD{

	private Prodotto[] prodotto;


	private Prodotto[] listaProdotto;

	private Impiegato impiegato;

	private Utente[] listaUtenti;
	
	private Utils utility;
	private Utente utente;

	public CITD(){
		utility = new Utils();
		utente = new Utente();
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
	
	public void inserisciNuovoUtente (Utente utente) {
		utente.set(utente);
	}
	
	public void updateUtente(String oldMatricola, Utente utente) {
		utente.edit(oldMatricola, utente);
	}
	
	public void eliminaUtente(String matricola) {
		utente.remove(new Utente(matricola));
	}
	
	public Prodotto visualizzaProdotto(char[] nome) {
		return null;
	}
	
	public Prodotto visualizzaProdotto(String nome) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			Prodotto prod = null;
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String name = data[0];
				String serial_number = data[1]; 
				if(name.equalsIgnoreCase(nome)){
					System.out.println("Product Found");
					prod = new Prodotto(name, serial_number);
					return prod;
				}else {
					System.out.println("Product not found");
				}
			}
			br.close();
			return prod;
		}catch(Exception e) {
			return null;
		}
	}

	public void modificaProdotto(Prodotto prodotto) {

	}

	public String eliminaProdotto(String numeroseriale) {
		try {
			Prodotto prod = new Prodotto();
			String product = prod.destroy(numeroseriale);
			return product;
		}catch(Exception e) {
			return null;
		}
	}

	public String aggiungiProdotto(String nome, String serial_number) {
		try {
			Prodotto prod = new Prodotto();
			String product = prod.aggiungiProdotto(nome, serial_number);
			return product;
		}catch(Exception e) {
			return null;
		}
	}


	

}
