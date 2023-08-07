import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class CITD{

	private Utils utility;

	private List<Utente> listaUtenti;
	private UtenteDao utenteDao;

	private List<Prodotto> listaProdotti;
	private ProdottoDao prodottoDao;

	private List<Richiesta> listaRichieste;
	private RichiestaDao richiestaDao;

	private Noleggio noleggio;

	public CITD(){
		
		utility = new Utils();

		utenteDao = new UtenteDao();
		listaUtenti = utenteDao.get();

		prodottoDao = new ProdottoDao(listaUtenti);
		listaProdotti = prodottoDao.get();

		richiestaDao = new RichiestaDao();
		listaRichieste = richiestaDao.get();

		// proiezioni = new Proiezioni();
		// noleggio = new Noleggio(null);
	}

	public List<Utente> getListaUtenti(){
		return listaUtenti;
	}
	
	public Utente Login(String matricola, String password) {
		
		for(Utente utente : listaUtenti)
			if(utente.getMatricola().equals(matricola)  && utente.getEncryptedPassword().equals(utility.getEncryptPassword(password)))
				return utente;
				
		System.out.println("Credenziali non valide");
		return null;
	}
	
	public Utente getDetailsUtente(String matricola) {

		for(Utente utente : listaUtenti)
			if(utente.getMatricola().equals(matricola))
				return utente;

		System.out.println("Utente non trovato");
		return null;
	}
	
	public void inserisciNuovoUtente (Utente user) {
		listaUtenti.add(user);
	}
	
	public void eliminaUtente(String matricola) {
		listaUtenti.remove(getDetailsUtente(matricola));
	}
	
	public void updateUtente(String oldMatricola, String matricola, String password, String nome, String cognome, String email, Boolean isSuperuser) {
		Utente utente = getDetailsUtente(oldMatricola);
		utente.setMatricola(matricola);
		if(!password.equalsIgnoreCase(""))
			utente.setPassword(utility.getEncryptPassword(password));
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setIsSuperuser(isSuperuser);
	}

	public void saveAllInDB(){
		utenteDao.set(listaUtenti);
		prodottoDao.set(listaProdotti);
		richiestaDao.set(listaRichieste);
	}

	//----------------------------PRODOTTI---------------------------

	public List<Prodotto> getListaProdotti(){
		return listaProdotti;
	}

	public Prodotto getDetailsProdotto(String iap) {
		for(Prodotto prodotto : listaProdotti)
			if(prodotto.getIAP().equalsIgnoreCase(iap))
				return prodotto;
		System.out.println("Prodotto non trovato");
		return null;
	}

	public void aggiungiProdotto(String nome, String iap, String serial_number, String tipo, String marca) {

		Prodotto newProdotto = new ProdottoBuilder()
									.nome(nome)
									.iap(iap)
									.serial_number(serial_number)
									.tipo(tipo)
									.marca(marca)
									.buildProdotto();
		
		listaProdotti.add(newProdotto);
	}

	public void eliminaProdotto(String iap) {
		listaProdotti.remove(getDetailsProdotto(iap));
	}

	public void modificaProdotto(String oldIAP, String nome, String iap, String serial_number, String tipo, String marca){
		Prodotto prodotto = getDetailsProdotto(oldIAP);
		prodotto.setNome(nome);
		prodotto.setIAP(iap);
		prodotto.setSerialNumber(serial_number);
		prodotto.setTipo(tipo);
		prodotto.setMarca(marca);
	}
	

	public String[][] getProiezioni(String possesso, String tipo, String costoMin,String costoMax, String impiegatoAssegnato, String date, String cadenza ) throws IOException, NumberFormatException{

        String filteredData[][] = new String[1][1];
        filteredData[0][0] = Integer.toString(new Proiezioni(listaProdotti).proiezione(possesso, tipo, costoMin, costoMax, impiegatoAssegnato, date, cadenza)) + "\u20AC";
        
        return filteredData;
		
	}

	public List<Manutenzione> getListaManutenzione(){
		List<Manutenzione> listaManutenzione = new ArrayList<>();
		for(Prodotto prodotto : listaProdotti){
			if (prodotto instanceof Manutenzione){
				listaManutenzione.add((Manutenzione)prodotto);
			}
		}
		return listaManutenzione;
	}
	
	public String[][] getDetailsManutenzione(){
		String[][] data = utility.listToMatrixString(getListaManutenzione());
		String[][] dataFiltered = new String[data.length][3];
		for (int i = 0; i<data.length; i++) {
			dataFiltered[i][0] = data[i][0]; //nome
			dataFiltered[i][1] = data[i][1]; //IAP
			dataFiltered[i][2] = data[i][9]; //stato
		}
		return dataFiltered;
	}
	
	public String[][] gestisciStatoManutenzione(String iap,String stato) {
		
		for(Manutenzione manutenzione : getListaManutenzione()){
			if(manutenzione.getIAP().equalsIgnoreCase(iap))
				manutenzione.setStato(stato);
				if(stato.equalsIgnoreCase("rifiutata"))
					manutenzione.setManutenzione("");
		}
		return getDetailsManutenzione();
	}

	public String[][] getCareRequest(String iap){
		List<Richiesta> listReqTemp = new ArrayList<>();
		for(Richiesta richiesta : listaRichieste)
			if(richiesta.getIAP().equalsIgnoreCase(iap))
				listReqTemp.add(richiesta);

		return utility.listToMatrixString(listReqTemp);
	}
	
	public String[][] getUserProduct(String matricola) {
		List<Prodotto> listaProdottiFiltered = new ArrayList<>();
		for(Prodotto prodotto : listaProdotti)
			if(prodotto.getUtente().getMatricola().equalsIgnoreCase(matricola))
				listaProdottiFiltered.add(prodotto);

		String[][] data = utility.listToMatrixString(listaProdottiFiltered);
		String[][] dataFiltered = new String[data.length][4];
		for (int i = 0; i<data.length; i++) {
			dataFiltered[i][0] = data[i][0]; //nome
			dataFiltered[i][1] = data[i][1]; //IAP
			dataFiltered[i][2] = data[i][9]; //tipo prodotto
			dataFiltered[i][3] = data[i][10]; //stato
		}
		return dataFiltered;
	}

	public void setProblemRequest(String iap, String problema){
		listaRichieste.add(new Richiesta(iap,problema));
		gestisciStatoManutenzione(iap, "lavorazione");
	}
	
	public String[][] getLicense(){
		String[][] data = new String[1][1];
		return data;
	}
	
	public String rinnovaScadenza(String iap, String scadenza) {
		getDetailsProdotto(iap).setScadenza(scadenza);
		return "done";
	}

	
	public Noleggio getNoleggio(){
		System.out.println("getNoleggio");
		return noleggio;
	}
}
