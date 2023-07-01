import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Noleggio extends Prodotto{

	String contattoAziendaTerza;
	Utils utils;
	
	public Noleggio(Prodotto pr, String contattoAziendaTerza) {
		this.setProdotto(pr.getNome(), pr.getIAP(), pr.getSerialNumber(), pr.getScadenza(), pr.getMarca());
		this.contattoAziendaTerza = contattoAziendaTerza;
	}
	
	public int giorniRimanenti(){

		String [] splStrings =  this.getScadenza().split("/");
		LocalDate dataFine = LocalDate.of(Integer.parseInt(splStrings[2]), Integer.parseInt(splStrings[1]), Integer.parseInt(splStrings[0]));
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), dataFine);

	}

	public String getContattoAziendaTerza(){
		return this.contattoAziendaTerza;
	}

	public void setContattoAziendaTerza(String contatto){
		String rewrite = "";
		try {
			String[][] productData = utils.getProductTableData(12);
			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equalsIgnoreCase(this.getIAP()) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						productData[row][11] = contatto;
					}
            	rewrite += rewrite + productData[row] +"\n";
				
			}

			FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rewrite);
	        bw.flush();
	        bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateNoleggio(String newScadenza){
		try {
			String[][] productData = utils.getProductTableData(12);

			String rewrite = "";

			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equalsIgnoreCase(this.getIAP()) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						productData[row][6] = newScadenza;
					}
            	rewrite += rewrite + productData[row] +"\n";
				
			}

			FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rewrite);
	        bw.flush();
	        bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}