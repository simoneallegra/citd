public class Prodotto {

	private String nome;

	private String serial_number;

	private String IAP;

	private String tipo;

	private String marca;

	private Utente utente;
	
	private String scadenza;

	private int costo; //indica il costo mensile

	private String tipoPossesso;

	private String stato;
	
	private String url;//per le licenze software

	private String pathDoc;
	
	/**
	 *  
	 */
	public Prodotto(String nome, String IAP, String serial_number, String tipo, String marca, Utente utente, String scadenza, int costo, String tipoPossesso, String stato, String url, String pathDoc) {
		this.nome = nome;
		this.serial_number = serial_number;
		this.IAP = IAP;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente = utente;
		this.scadenza = scadenza;
		this.costo = costo;
		this.tipoPossesso = tipoPossesso;
		this.stato = stato;
		this.url = url;
		this.pathDoc = pathDoc;
	}

	public String getNome() {
		return nome;
	}

	public String getSerialNumber() {
		return serial_number;
	}

	public String getIAP() {
		return IAP;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getMarca() {
		return marca;
	}

	public Utente getUtente() {
		if(utente == null)
			//Utente Fake
			return new Utente("", "", "", "", "", false);
		return utente;
	}
	
	public String getScadenza() {
		return scadenza;
	}

	public int getCosto() {
		return costo;
	}

	public String getTipoPossesso() {
		return tipoPossesso;
	}

	public String getStato() {
		return stato;
	}

	public String getUrl() {
		return url;
	}

	public String getPathDoc() {
		return pathDoc;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSerialNumber(String serialnumber) {
		this.serial_number = serialnumber;
	}
	
	public void setIAP(String iap) {
		this.IAP = iap;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public void setCosto(int costo) {
		 this.costo = costo;
	}

	public void setTipoPossesso(String tipoPossesso) {
		 this.tipoPossesso = tipoPossesso;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPathDoc(String pathDoc) {
		this.pathDoc = pathDoc;
	}

	public String toString(){
		return nome+","+IAP+","+serial_number+","+marca+","+((utente != null) ? utente.getMatricola(): "null")+","+scadenza+","+costo+","+tipo+","+","+stato+","+url+","+pathDoc;
	}
	
}
