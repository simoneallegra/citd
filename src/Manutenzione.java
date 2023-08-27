public class Manutenzione extends Prodotto{

	private String manutenzione;

	private String statoRichiesta;

	public Manutenzione(Prodotto prodotto, String manutenzione, String statoRichiesta) {
		super(
			prodotto.getNome(),
			prodotto.getIAP(),
			prodotto.getSerialNumber(),
			prodotto.getTipo(),
			prodotto.getMarca(),
			prodotto.getUtente(),
			prodotto.getScadenza(),
			prodotto.getCosto(),
			prodotto.getTipoPossesso()
		);
		this.manutenzione = manutenzione;
		this.statoRichiesta = statoRichiesta;
	}
	
	public String getManutenzione() {
		return manutenzione;
	}

	public void setManutenzione(String manutenzione) {
		this.manutenzione = manutenzione;
	}
	
	public String getStatoRichiesta() {
		return statoRichiesta;
	}

	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}	

	public String toString(){
		return this.getNome() + "," 
		+ this.getIAP() + ","
		+ this.getSerialNumber() + ","
		+ this.getTipo() + ","
		+ this.getMarca() + ","
		+ ((this.getUtente() != null) ? this.getUtente().getMatricola(): "null") + ","
		+ this.getScadenza() + ","
		+ this.getCosto() + ","
		+ this.getTipoPossesso() + ","
		+ manutenzione + ","
		+ statoRichiesta + ","
		+ "null,"
		+ "null";
	}
	
}