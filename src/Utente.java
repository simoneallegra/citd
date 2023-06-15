import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utente extends Amministratore {

	private String nome;

	private String cognome;

	public String matricola;
	
	private String password;

	private String email;

	private boolean isLogged;

	private Impiegato impiegato;

	private Amministratore amministratore;
	
	public Utils utility;
	
	public Utente(){
		utility = new Utils();
	}

	public Utente(String matricola, String password){
		this.matricola = matricola;
		this.password = password;
		utility = new Utils();
	}
	
	public Utente(String matricola){
		this.matricola = matricola;
		utility = new Utils();
	}
	
	public String getEncryptedPassword() {
		return this.password;
	}
	
	
	public Utente get(String matricola) {
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
	public void set(Utente utente) {
		try {
			String newField = utente.matricola + "," + utility.getEncryptPassword(utente.getEncryptedPassword())+'\n';
			System.out.println(newField);
			Files.write(Paths.get("./database/db_users.txt"), newField.getBytes(), StandardOpenOption.APPEND);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void edit(String oldMatricola, Utente utente) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[2];
					data = s.split(",");
					
					if((data[0].equals(oldMatricola))){
						Utente utenteTrovato = this.get(oldMatricola);
						utente.password = utenteTrovato.getEncryptedPassword();
						this.remove(new Utente(oldMatricola));
						this.set(utente);
						return;
					}
					
				}
			
				br.close();
		}catch(Exception e) {}
		
		return;
	}
	
	public void remove(Utente utente) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			String s,prova = "";
			Boolean found = false;
			System.out.println(utente.matricola);
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String matricola = data[0];
				System.out.println(matricola);
				if(!matricola.equalsIgnoreCase(utente.matricola)){
			        prova = prova + s + '\n';
			    }else {
					found = true;
				}
			}
			FileWriter fw = new FileWriter("./database/db_users.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
	        System.out.println(prova);
			bw.write(prova);
			br.close();
	        bw.flush();
	        bw.close();
	        
	        if(found) {
	        	System.out.println("eliminato");
	        	return;
	        }else {
	        	System.out.println("non to");
	        }
		        
				

		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	

}
