public class Utente extends Amministratore {

	private String nome;

	private String cognome;

	public String matricola;

	private String email;

	private boolean isLogged;

	private Impiegato impiegato;

	private Amministratore amministratore;

	public Utente(String matricola){
		this.matricola = matricola;
	}
	
	public void setUtente(int nome, int cognome, int email) {

	}
	

}
