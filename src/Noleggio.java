import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Noleggio extends Prodotto{

	String documentoNoleggio;
	
	public Noleggio(Prodotto prodotto, String documentoNoleggio) {
		super(
			prodotto.getNome(),
			prodotto.getIAP(),
			prodotto.getSerialNumber(),
			prodotto.getTipo(),
			prodotto.getMarca(),
			prodotto.getUtente(),
			prodotto.getScadenza(),
			prodotto.getCosto(),
			"noleggio"/*,
			prodotto.getUrl()*/
		);
		this.documentoNoleggio = documentoNoleggio;
	}

	public String getDocumentoNoleggio() {
		return documentoNoleggio;
	}

	public void setDocumentoNoleggio(String filePath, String endFilePath) throws IOException{

		File saveDirectory = new File(endFilePath);

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

		this.documentoNoleggio = newName;
	}
	
	public int giorniRimanenti(){
		
		// String [] splStrings =  scadenza.split("/");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
			Date dateScadenza = formatter.parse(this.getScadenza());
			Date today = new Date();	
			long diffInMillies = dateScadenza.getTime() - today.getTime();
			return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int openDocumentoNoleggio() throws IOException{
		System.out.println("openDocument");

		String path = "./documents/" + this.documentoNoleggio;
		File pdfFile = new File(path);
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

		return 1;
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
		+ "noleggio" + ","
		+ "null,"
		+ "null,"
		+ "null,"
		+ documentoNoleggio;
	}
	
	
}