public class ProdottoBuilder {

	private String _nome;
	private String _serial_number;
	private String _IAP;
	private String _tipo;
	private String _marca;
	private Utente _utente = null;
	private String _scadenza = "null";
	private int _costo = 0;
	private String _tipoPossesso = "acquisto";
	private String _url = "null";

	public ProdottoBuilder(){}

	public Prodotto buildProdotto(){
		return new Prodotto(_nome, _IAP, _serial_number, _tipo, _marca, _utente, _scadenza, _costo, _tipoPossesso, _url);
	}

	public ProdottoBuilder nome(String _nome){
		this._nome = _nome;
		return this;
	}

	public ProdottoBuilder serial_number(String _serial_number){
		this._serial_number = _serial_number;
		return this;
	}

	public ProdottoBuilder iap(String _IAP){
		this._IAP = _IAP;
		return this;
	}

	public ProdottoBuilder tipo(String _tipo){
		this._tipo = _tipo;
		return this;
	}

	public ProdottoBuilder marca(String _marca){
		this._marca = _marca;
		return this;
	}

	public ProdottoBuilder utente(Utente _utente){
		this._utente = _utente;
		return this;
	}

	public ProdottoBuilder scadenza(String _scadenza){
		this._scadenza = _scadenza;
		return this;
	}

	public ProdottoBuilder costo(int _costo){
		this._costo = _costo;
		return this;
	}

	public ProdottoBuilder tipoPossesso(String _tipoPossesso){
		this._tipoPossesso = _tipoPossesso;
		return this;
	}

	public ProdottoBuilder url(String _url){
		this._url = _url;
		return this;
	}

}