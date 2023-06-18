import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utente{

	public String nome;

	public String cognome;

	public String matricola;
	
	public String password;

	public String email;

	public int superuser;

	
	public Utils utility;
	
	public Utente(){
		utility = new Utils();
	}

	public Utente(String matricola){
		this.matricola = matricola;
		utility = new Utils();
	}

	public Utente(String matricola, String password){
		this.matricola = matricola;
		this.password = password;
		utility = new Utils();
	}
	
	public Utente(String matricola, String password, String nome, String cognome, String email, boolean superuser){
		this.matricola = matricola;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.superuser = superuser ? 1 : 0;
		utility = new Utils();
	}
	
	
	
	public Utente get(String matricola) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[6];
					data = s.split(",");
					
					
					if((data[0].equals(matricola))){
						System.out.println("login done");
						return new Utente(data[0], data[1], data[2], data[3], data[4], Boolean.valueOf(data[5]));
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
			System.out.println(utente.password);
			String newField = 
				utente.matricola + "," 
				+ utility.getEncryptPassword(utente.password) + ","
				+ utente.nome + "," 
				+ utente.cognome + "," 
				+ utente.email + "," 
				+ utente.superuser +'\n';

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
						utente.password = utenteTrovato.password;
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
