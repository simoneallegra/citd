import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Prodotto {

	private String nome;

	private String serial_number;

	private int IAP;

	private int tipo;

	private int barcode;

	private int marca;

	private boolean inStock;

	/**
	 *  
	 */
	public Prodotto(String nome, String serial_number, int IAP, int tipo, int marca, int barcode, boolean inStock) {

	}

	public Prodotto() {
		
	}
	
	public Prodotto(String nome, String serial_number) {
		this.nome = nome;
		this.serial_number = serial_number;
	}

	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + ", serial_number=" + serial_number + "]";
	}

	public Prodotto getProdotto(char[] nome) {
		return null;
	}

	public void setProdotto(Prodotto prodotto) {

	}

	public String destroy(String serial_number) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			//PrintWriter outputStream = new PrintWriter(new FileWriter("./database/db_product.txt"));

	        FileWriter fw = new FileWriter("./database/db_product.txt",true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        /*
	        bw.write(line);
	        bw.write("\n");
	        bw.flush();
	        bw.close();
			*/
			String s = "";
			Boolean found = false;
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String numeroseriale = data[1];
				if(!numeroseriale.equalsIgnoreCase(serial_number)){
			        bw.write(s);
			        bw.write("\n");
			    }else {
					found = true;
				}
			}
			br.close();
	        bw.flush();
	        bw.close();
	        if(found) {
				return "Prodotto eliminato dal DB";
			}else {
				return "Prodotto non presente in DB";				
			}
		}catch(Exception e) {
			return "Errore durante l'eliminazione del prodotto";
		}
	}

	public void edit(Prodotto prodotto) {

	}

	public String aggiungiProdotto(String nome, String serial_number) {
		try {
			String line = nome + "," + serial_number;
	        FileWriter fw = new FileWriter("./database/db_product.txt", true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(line);
	        bw.write("\n");
	        bw.flush();
	        bw.close();
            return "Prodotto aggiunto al DB";
		}catch(Exception e) {
			return null;
		}
	}
	
}
