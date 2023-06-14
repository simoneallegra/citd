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
	private Utente utenti;

	public CITD(){
		utility = new Utils();
	}
	
	public Utente getDetailsUtente(String matricola) {

		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[2];
					data = s.split(",");
					
					
					if((data[0].equals(matricola))){
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
	
	public void inserisciNuovoUtente (Utente utente) {
		
		try {
			String newField = utente.matricola + "," + utility.getEncryptPassword(utente.getEncryptedPassword());
			System.out.println(newField);
			Files.write(Paths.get("./database/db_users.txt"), newField.getBytes(), StandardOpenOption.APPEND);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void updateUtente(Utente utente) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[2];
					data = s.split(",");
					
					if((data[0].equals(utente.matricola))){
						this.eliminaUtente(utente.matricola);
						this.inserisciNuovoUtente(utente);
						return;
					}else {
						System.out.println("wrong credentials");
					}
					
				}
			
				br.close();
		}catch(Exception e) {}
		
		return;
	}
	
	public boolean eliminaUtente(String matricola) {
		try {
			File inputFile = new File("./database/db_users.txt");
			File tempFile = new File("myTempFile.txt");
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String s;
	
			while((s = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
				String data[] = new String[2];
				data = s.split(",");
			    if((data[0].equals(matricola))) continue;
			    writer.write(s + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close();
			boolean successful = tempFile.renameTo(inputFile);
			return successful;
		}catch(Exception e) {
			return false;
		}
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
