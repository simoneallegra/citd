import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Noleggio extends Prodotto{

	String documentoNoleggio;
	Utils utils;
	
	
	public Noleggio(String documentoNoleggio) {
		this.documentoNoleggio = documentoNoleggio;
		utils = new Utils();
	}

	public String [][] getListaNoleggi() {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_products.txt"));
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
					int giorniRimanenti = this.giorniRimanenti(split[6]);
					if(giorniRimanenti < 0) dati[2] = "SCADUTO";
					else dati[2] = String.valueOf(giorniRimanenti) + " giorni";
					//dati[2] = split[6];
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
			e.printStackTrace();
			return null;
		}

	}

// 	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
//     long diffInMillies = date2.getTime() - date1.getTime();
//     return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
// }
	
	public int giorniRimanenti(String scadenza){
		
		// String [] splStrings =  scadenza.split("/");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
			Date dateScadenza = formatter.parse(scadenza);
			Date today = new Date();

			long diffInMillies = dateScadenza.getTime() - today.getTime();

			return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			e.printStackTrace();
		}


		return 0;
		// String [] splStrings =  scadenza.split("/");
		// LocalDate dataFine = Date.of(Integer.parseInt(splStrings[2]), Integer.parseInt(splStrings[1]), Integer.parseInt(splStrings[0]));
		// return (int) ChronoUnit.DAYS.between(LocalDate.now(), dataFine);

	}

	public int openDocumentoNoleggio(String iap) throws IOException{
		System.out.println("openDocument");

		String namepath = "null";

		String[][] productData = utils.getProductTableData(13);
			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equals(iap) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						namepath = productData[row][12];
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
					return 0;
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

			
		}
		return 1;
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
			String[][] productData = utils.getProductTableData(13);
			for (int row = 0; row < productData.length; row++) {
					if(productData[row][1].equals(iap) && productData[row][8].equals("noleggio")){
						//Elemento trovato -> aggiorna scadenza
						productData[row][12] = newName;
					}
				
				String joined = "";
				for(int i = 0; i< productData[row].length;i++){
					if(i == productData[row].length){
						joined += productData[row][i];
						break;
					}
					joined += productData[row][i] + ",";
				}
            	rewrite += joined +"\n";
			}

				

			FileWriter fw = new FileWriter("./database/db_products.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rewrite);
	        bw.flush();
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateNoleggio(String iap,String newScadenza){
		Prodotto prodotto = new Prodotto(iap);
		prodotto.setScadenza(newScadenza);
	}
	
}