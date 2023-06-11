public interface CITDamministratore {

	public static final CITD cITD = new CITD();

	public void aggiungiProdotto(Prodotto prodotto);

	public void eliminaProdotto(int IAPprodotto);

	public void inserisciNuovoUtente(Utente utente);

	public void eliminaUtente(Utente utente);

}
