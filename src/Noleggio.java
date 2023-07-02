import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
public class Noleggio extends Prodotto{

	String documentoNoleggio;
	Utils utils;
	
	public Noleggio(Prodotto pr, String documentoNoleggio) {
		this.setProdotto(pr.getNome(), pr.getIAP(), pr.getSerialNumber(), pr.getScadenza(), pr.getMarca());
		this.documentoNoleggio = documentoNoleggio;
		utils = new Utils();
	}

	public String [][] getListaNoleggi() {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			int i = 0;
			int counter = 0;
			ArrayList <String[]>listOfArrays = new ArrayList<>();
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				if(split[8].equalsIgnoreCase("noleggio")) {
					String dati[] =  new String[3];
					counter++;
					dati[0] = split[0];
					dati[1] = split[1];
					// dati[2] = String.valueOf(this.giorniRimanenti(split[6]));
					dati[2] = split[6];
					listOfArrays.add(dati);
				}	
				i++;
			}
			int riga=0;
			data = new String[counter][3];
	        // Copia gli elementi non nulli nella nuova matrice
	        for (String[] array : listOfArrays) {
				int column=0;
	            for (String element : array) {
	            	data[riga][column]= element;
	            	column++;
	            }
	            riga++;
	        }
			br.close();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public int giorniRimanenti(String scadenza){

		String [] splStrings =  scadenza.split("/");
		LocalDate dataFine = LocalDate.of(Integer.parseInt(splStrings[2]), Integer.parseInt(splStrings[1]), Integer.parseInt(splStrings[0]));
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), dataFine);

	}

	public void openDocumentoNoleggio(String iap) throws IOException{
		System.out.println("openDocument");

		String namepath = "null";

		String[][] productData = utils.getProductTableData(12);
			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equals(iap) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						namepath = productData[row][11];
					}
				}

		if(!namepath.equals("null")){
			String path = "./documents/" + namepath;
			System.out.println(path);
			File pdfFile = new File(path);
			System.out.println(pdfFile);
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

			System.out.println("Done");
		}

	}

	public void setDocumentoNoleggio(String iap, String filePath) throws IOException{

		File saveDirectory = new File("./Documents");

		FileInputStream fileInput = new FileInputStream(filePath);

		String newName = String.valueOf((int)(Math.random()*1000)) + ".pdf";

		String savePath = saveDirectory.getAbsolutePath()+'/'+ newName;

		FileOutputStream fileOutput = new FileOutputStream(savePath);

		byte[] buffer = new byte[1024];
        int length;

		while ((length = fileInput.read(buffer)) > 0) {
			fileOutput.write(buffer, 0, length);
		}
		
		// Chiusura degli stream
		fileInput.close();
		fileOutput.close();

		String rewrite = "";
		try {
			String[][] productData = utils.getProductTableData(12);
			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equals(iap) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						productData[row][11] = newName;
					}
				
				String joined = "";
				for(int i = 0; i< productData[row].length;i++){
					joined += productData[row][i] + ",";
				}
            	rewrite += rewrite +joined +"\n";
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

	public void updateNoleggio(String nome,String newScadenza){
		Prodotto prodotto = new Prodotto(nome,"");
		prodotto.setScadenza(newScadenza);
	}
	
}