public class RichiestaProdotto {

    private String utente;
    private String nomeProdotto;
    private String tipo;
    private String marca;
    private String motivo;
    private String stato;
    private String iap;

    
    public RichiestaProdotto(){

    }


	public RichiestaProdotto(String iap, String utente, String nomeProdotto, String tipo, String marca, String motivo) {
		this.iap = iap;
		this.utente = utente;
		this.nomeProdotto = nomeProdotto;
		this.tipo = tipo;
		this.marca = marca;
		this.motivo = motivo;
		this.stato = "In attesa di approvazione";
	}

	public RichiestaProdotto(String iap, String utente, String nomeProdotto, String tipo, String marca, String motivo, String stato) {
		this.iap = iap;
		this.utente = utente;
		this.nomeProdotto = nomeProdotto;
		this.tipo = tipo;
		this.marca = marca;
		this.motivo = motivo;
		this.stato = stato;
	}


	public String getStato() {
		return stato;
	}


	public void setStato(String stato) {
		this.stato = stato;
	}


	public String getIap() {
		return iap;
	}


	public void setIap(String iap) {
		this.iap = iap;
	}


	public String getUtente() {
		return utente;
	}


	public void setUtente(String utente) {
		this.utente = utente;
	}


	public String getNomeProdotto() {
		return nomeProdotto;
	}


	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	@Override
	public String toString() {
		return  iap + "," + utente + "," +
				nomeProdotto + "," + tipo + ","
				+ marca + "," + motivo + "," + stato;
	}
    
}
