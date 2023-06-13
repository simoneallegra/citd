import java.io.BufferedReader;
import java.io.FileReader;

public class Utente extends Amministratore {

	private String nome;

	private String cognome;

	public String matricola;
	
	private String password;

	private String email;

	private boolean isLogged;

	private Impiegato impiegato;

	private Amministratore amministratore;

	public Utente(String matricola, String password){
		this.matricola = matricola;
		this.password = password;
	}
	
	public String getEncryptedPassword() {
		return this.password;
	}
	
	
	
	public void setUtente(int nome, int cognome, int email) {

	}
	

}
