import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDao{

	private List<Prodotto> prodotti;
	private List<Utente> utenti;

	public ProdottoDao(List<Utente> listaUtenti){
		prodotti = new ArrayList<>();
		this.utenti = listaUtenti;
	}


	
	public List<Prodotto> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_products.txt"));
			String s = "";
			while((s = br.readLine()) != null){
				String data[] = new String[12];
				data = s.split(",");

				Utente utenteTrovato = null;
				for(Utente utente : utenti)
					if(utente.getMatricola().equalsIgnoreCase(data[5]))
						utenteTrovato = utente;	
						
				Prodotto nuovoProdotto = new Prodotto(data[0], data[1], data[2], data[3], data[4], utenteTrovato, data[6], Integer.parseInt(data[7]), data[8], data[11]);
				if(data[9].equalsIgnoreCase("manutenzione"))
					prodotti.add(new Manutenzione(nuovoProdotto, data[9], data[10]));
				else if(data[8].equalsIgnoreCase("noleggio"))
					prodotti.add(new Noleggio(nuovoProdotto, data[12]));
				// else if(data[10].equalsIgnoreCase("abbonamento"))
				// 	prodotti.add(new Noleggio(nuovoProdotto, data[11]));
				else
					prodotti.add(nuovoProdotto);
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return prodotti;
	}

	public void set(List<Prodotto> newProdotti) {


		try {
			PrintWriter writer = new PrintWriter("./database/db_products.txt");
			
			String fields = "";
			for(Prodotto prodotto : newProdotti){
				fields += 
					prodotto.getNome() + "," 
					+ prodotto.getIAP() + ","
					+ prodotto.getSerialNumber() + "," 
					+ prodotto.getTipo() + "," 
					+ prodotto.getMarca() + "," 
					+ prodotto.getUtente().getMatricola() + "," 
					+ prodotto.getScadenza() + "," 
					+ String.valueOf(prodotto.getCosto()) + "," 
					+ prodotto.getTipoPossesso() + "," 
					+ ((prodotto instanceof Manutenzione) ? ((Manutenzione)prodotto).getManutenzione() : "null") + "," 
					+ ((prodotto instanceof Manutenzione) ? ((Manutenzione)prodotto).getStatoRichiesta() : "null") + "," 
					+ prodotto.getUrl() + "," 
					+ ((prodotto instanceof Noleggio) ? ((Noleggio)prodotto).getDocumentoNoleggio() : "null" ) +'\n';
			}
			
			writer.print(fields);
			writer.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

}
