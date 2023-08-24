import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class CITD{

	private Utils utility;

	private List<Utente> listaUtenti;
	private UtenteDao utenteDao;

	private List<Prodotto> listaProdotti;
	private ProdottoDao prodottoDao;

	private List<Richiesta> listaRichieste;
	private RichiestaDao richiestaDao;

	private List<RichiestaProdotto> listaRichiesteProdotto;
	private RichiestaProdottoDao richiestaProdottoDao;
	
	private Noleggio noleggio;

	public CITD(){
		
		utility = new Utils();

		utenteDao = new UtenteDao();
		listaUtenti = utenteDao.get();

		prodottoDao = new ProdottoDao(listaUtenti);
		listaProdotti = prodottoDao.get();

		richiestaDao = new RichiestaDao();
		listaRichieste = richiestaDao.get();
		
		richiestaProdottoDao = new RichiestaProdottoDao();
		listaRichiesteProdotto = richiestaProdottoDao.get();

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
		richiestaProdottoDao.set(listaRichiesteProdotto);
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
	
	public void aggiungiProdotto(String nome, String iap, String serial_number, String tipo, String marca, String tipoPossesso, int costo, String scadenza, String url) {
		//manca utente, di default null
		Prodotto newProdotto = new ProdottoBuilder()
									.nome(nome)
									.iap(iap)
									.serial_number(serial_number)
									.tipo(tipo)
									.marca(marca)
									.tipoPossesso(tipoPossesso)
									.costo(costo)
									.scadenza(scadenza)
									.url(url)
									.buildProdotto();
		
		listaProdotti.add(newProdotto);
	}
	
	public void aggiungiProdotto(String nome, String iap, String serial_number, String tipo, String marca, Utente utente, String tipoPossesso, int costo, String scadenza, String url) {
		//manca utente, di default null
		Prodotto newProdotto = new ProdottoBuilder()
									.nome(nome)
									.iap(iap)
									.serial_number(serial_number)
									.tipo(tipo)
									.marca(marca)
									.utente(utente)
									.tipoPossesso(tipoPossesso)
									.costo(costo)
									.scadenza(scadenza)
									.url(url)
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

	public List<Noleggio> getListaNoleggi(){
		List<Noleggio> listaNoleggi = new ArrayList<>();
		for(Prodotto prodotto : listaProdotti){
			if (prodotto instanceof Noleggio){
				listaNoleggi.add((Noleggio)prodotto);
			}
		}
		return listaNoleggi;
	}

	public String[][] getTableNoleggi(){
		String[][] data = utility.listToMatrixString(getListaNoleggi());
		String[][] dataFiltered = new String[data.length][3];
		for (int i = 0; i<data.length; i++) {
			dataFiltered[i][0] = data[i][0]; //nome
			dataFiltered[i][1] = data[i][1]; //IAP
			dataFiltered[i][2] = data[i][6]; //scadenza
		}
		return dataFiltered;
	}

	public Richiesta getRichiesta(String iap){
		for(Richiesta richiesta : listaRichieste)
			if(richiesta.getIAP().equalsIgnoreCase(iap))
				return richiesta;
		return null;
	}

	public Noleggio getNoleggio(String iap){
		for(Noleggio noleggio : getListaNoleggi())
			if(noleggio.getIAP().equalsIgnoreCase(iap))
				return noleggio;
		return null;
	}
	
	public void setManutenzione(String iap, String stato){
		for(Prodotto prodotto : listaProdotti) {
			if(prodotto.getIAP().equalsIgnoreCase(iap)) {
				Manutenzione manutenzione = new Manutenzione(prodotto, "manutenzione", stato);
				listaProdotti.set(listaProdotti.indexOf(prodotto), manutenzione);
			}
		}
	}
	
	public void deleteMaintenanceRequest(String iap) {
		try {
			for(Richiesta req: listaRichieste) {
				if(req.getIAP().equalsIgnoreCase(iap))
					listaRichieste.remove(req);
			}			
		}catch(Exception e) {
			
		}
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
			if(!prodotto.getTipo().equalsIgnoreCase("software") && !prodotto.getTipoPossesso().equalsIgnoreCase("noleggio")) {
				if(prodotto instanceof Manutenzione ) {
					listaProdottiFiltered.add(prodotto);
				} else if(prodotto.getUtente().getMatricola().equalsIgnoreCase(matricola)) {
					listaProdottiFiltered.add(prodotto);
				}
			}
		String[][] data = utility.listToMatrixString(listaProdottiFiltered);
		String[][] dataFiltered = new String[data.length][4];
			for (int i = 0; i<data.length; i++) {
				dataFiltered[i][0] = data[i][0]; //nome
				dataFiltered[i][1] = data[i][1]; //IAP
				if(data[i][9].equalsIgnoreCase("null")) {
					dataFiltered[i][2] = "funzionante";
					dataFiltered[i][3] = "-";
				}else {
					dataFiltered[i][2] = data[i][9]; //tipo prodotto
					dataFiltered[i][3] = data[i][10]; //stato	
				}
			}			
		return dataFiltered;
	}
	
	//ritorna
	public String[][] getAllMaintenanceProduct(){
		List<Prodotto> listaProdottiFiltered = new ArrayList<>();
		for(Prodotto prodotto : listaProdotti)
			if(!prodotto.getTipo().equalsIgnoreCase("software") && !prodotto.getTipoPossesso().equalsIgnoreCase("noleggio") && prodotto instanceof Manutenzione) {
					listaProdottiFiltered.add(prodotto);
			}
		
		String[][] data = utility.listToMatrixString(listaProdottiFiltered);
		String[][] dataFiltered = new String[data.length][3];
		for (int i = 0; i<data.length; i++) {
			dataFiltered[i][0] = data[i][0]; //nome
			dataFiltered[i][1] = data[i][1]; //IAP
			dataFiltered[i][2] = data[i][10]; //stato
		}
		return dataFiltered;

	}
	
	
	
	
	
	public String[][] reduceObject (String data[][], ArrayList<String> campi){
		String[][] dataFiltered = new String[data.length][campi.size()];
		for (int i = 0; i<data.length; i++) {
			/*dataFiltered[i][0] = data[i][0]; //nome
			dataFiltered[i][1] = data[i][1]; //IAP
			if(data[i][9].equalsIgnoreCase("null")) {
				dataFiltered[i][2] = "funzionante";
				dataFiltered[i][3] = "-";
			}else {
				dataFiltered[i][2] = data[i][9]; //tipo prodotto
				dataFiltered[i][3] = data[i][10]; //stato	
			}*/
			
			
		}
		return dataFiltered;
		
	}
	

	public void setProblemRequest(String iap, String problema){
		listaRichieste.add(new Richiesta(iap,problema));
		//gestisciStatoManutenzione(iap, "lavorazione");
	}
	
	public String rinnovaScadenza(String iap, String scadenza) {
		getDetailsProdotto(iap).setScadenza(scadenza);
		return "done";
	}

	
	public Noleggio getNoleggio(){
		System.out.println("getNoleggio");
		return noleggio;
	}
	
	//--------------RICHIESTE NUOVI PRODOTTI
	
	public List<RichiestaProdotto> getListaRichiestaNuovoProdotto(Boolean superuser, String matricola){
		if(superuser) {
			return listaRichiesteProdotto;	
		}else {
			List<RichiestaProdotto> richiesteUtente= new ArrayList<RichiestaProdotto>();
			for(RichiestaProdotto req : listaRichiesteProdotto) {
				if(req.getUtente().equalsIgnoreCase(matricola)) {
					richiesteUtente.add(req);					
				}
			}
			return richiesteUtente;
		}
	}
	
	public void aggiungiRichiestaNuovoProdotto(String iap,String nome, String tipo, String marca, String motivo, String utente) {
		RichiestaProdotto reqProd = new RichiestaProdotto(iap, utente, nome, tipo, marca, motivo);
		listaRichiesteProdotto.add(reqProd);
	}
	
	
	public void deleteNewProductRequest(String iap) {
		try {
			for(RichiestaProdotto req: listaRichiesteProdotto) {
				if(req.getIap().equalsIgnoreCase(iap))
					listaRichiesteProdotto.remove(req);
			}			
		}catch(Exception e) {
			
		}
	}
	
	
	public List<Abbonamento> getListaAbbonamenti(){
		List<Abbonamento> listaAbbonamenti = new ArrayList<>();
		for(Prodotto prodotto : listaProdotti){
			if (prodotto instanceof Abbonamento){
				int risultato=0;			
			        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			        Date scad=null;
			        try {
			            scad = formatoData.parse(prodotto.getScadenza());
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
			        Date dataOggi = new Date();
			        risultato = scad.compareTo(dataOggi);
			        if(risultato<0)
			        	//se risultato è minore di 0, la scadenza è precedente alla data di oggi
			        	listaAbbonamenti.add((Abbonamento)prodotto);
			}
		}
		return listaAbbonamenti;
	}

	public Abbonamento getAbbonamento(String iap) {
		for(Prodotto prodotto : listaProdotti){
			if (prodotto instanceof Abbonamento && prodotto.getIAP().equalsIgnoreCase(iap)){
				return (Abbonamento)prodotto;
			}
		}
		return null;
	}
	
}
