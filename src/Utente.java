public class Utente{

	private String nome;
	private String cognome;
	private String matricola;
	private String password;
	private String email;
	private boolean superuser;
	
	public Utente(String matricola, String password, String nome, String cognome, String email, boolean superuser){
		this.matricola = matricola;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.superuser = superuser;
	}

	public String getNome(){
		return nome;
	}

	public String getCognome(){
		return cognome;
	}

	public String getMatricola(){
		return matricola;
	}

	public String getEncryptedPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}

	public Boolean getIsSuperuser(){
		return superuser;
	}

	public void setNome(String stringa){
		nome = stringa;
	}

	public void setCognome(String stringa){
		cognome = stringa;
	}

	public void setMatricola(String stringa){
		matricola = stringa;
	}

	public void setPassword(String stringa){
			password = stringa;
	}

	public void setEmail(String stringa){
		email = stringa;
	}

	public void setIsSuperuser(Boolean value){
		superuser = value;
	}

	public String toString(){
		return matricola+","+ nome+","+ cognome+","+ email+","+ (superuser ? "superuser" : "user");
	}
}
