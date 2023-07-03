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



public class CITD{

	
	private Utils utility;
	private Utente utente;
	private Prodotto prod;
	private Proiezioni proiezioni;
	private Manutenzione manutenzione;
	private Noleggio noleggio;

	public CITD(){
		utility = new Utils();
		utente = new Utente();
		prod = new Prodotto();
		proiezioni = new Proiezioni();
		manutenzione = new Manutenzione();
		noleggio = new Noleggio(prod, null);
	}
	
	public Utente Login(String matricola, String password) {
		
		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			
			
			String s = "";
				while((s = br.readLine()) != null){
					String data[] = new String[2];
					data = s.split(",");
					
					
					if((data[0].equals(matricola)) && (data[1].equals(utility.getEncryptPassword(password)))){
						System.out.println("login done");
						return new Utente(data[0], data[1], data[2], data[3], data[4], Boolean.valueOf(data[5]));
					}else {
						System.out.println("wrong credentials");
					}
					
				}
			
				br.close();
		}catch(Exception e) {}
		
		return null;
	}
	
	public Utente getDetailsUtente(String matricola) {
		return utente.get(matricola);
	}
	
	public void inserisciNuovoUtente (Utente user) {
		System.out.println(user.password);
		utente.set(user, true);
	}
	
	public void updateUtente(String oldMatricola, Boolean passwordUpdate, Utente user) {
		utente.edit(oldMatricola, passwordUpdate, user);
	}
	
	public void eliminaUtente(String matricola) {
		utente.remove(new Utente(matricola));
	}
	
	
	public Prodotto visualizzaProdotto(String nome) {
		Prodotto prodotto;
		try {
			prodotto = prod.visualizza(nome);
			if(prodotto == null) {
				return null;
			}else {
				return prodotto;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Prodotto modificaProdotto(String prodotto) {
		try{
			prod = prod.edit(prodotto);
			return prod;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String eliminaProdotto(String iap) {
		try {
			String product = prod.destroy(iap);
			return product;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String aggiungiProdotto(String nome, String iap, String serial_number, String tipo, String marca) {
		try {
			String product = prod.aggiungiProdotto(nome, iap, serial_number, tipo, marca);
			return product;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String[][] getProiezioni(String possesso, String tipo, String costoMin,String costoMax, String impiegatoAssegnato, String date, String cadenza ) throws IOException, NumberFormatException{

        String filteredData[][] = new String[1][1];
        filteredData[0][0] = Integer.toString(proiezioni.proiezione(possesso, tipo, costoMin, costoMax, impiegatoAssegnato, date, cadenza)) + "\u20AC";
        
        return filteredData;
		
	}
	
	public String[][] getMaintenance(){
		String data[][] = manutenzione.getMaintenanceProduct();
		return data;
	}
	
	public String[][] acceptMaintenance(String iap,String stato) {
		manutenzione.acceptMaintenance(iap,stato, false);
		String data[][] = getMaintenance();
		return data;
	}
	
	public String[][] getLicense(){
		String data[][] = prod.getLicense();
		return data;
	}

	public String getUrl(String iap){
		String url = prod.getUrl(iap);
		return url;
	}
	
	public String rinnovaScadenza(String iap, String scadenza) {
		String controllo =prod.rinnovaScadenza(iap, scadenza);
		return controllo;
	}
	
	public String[][] getUserProduct(String matricola) {
		String data[][] = prod.getUserProduct(matricola);
		return data;
	}
	
	public void setProblemRequest(String iap, String problema){
		manutenzione.setProblemRequest(iap,problema);
		manutenzione.acceptMaintenance(iap, "lavorazione", true);
	}

	public String[][] getCareRequest(String iap){
		String data[][]=manutenzione.getCareRequest(iap);
		return data;
	}

	
	public Noleggio getNoleggio(){
		System.out.println("getNoleggio");
		return noleggio;
	}
}
