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

public class Abbonamento extends Prodotto{

	String url;
	
	public Abbonamento(Prodotto prodotto, String url) {
		super(
			prodotto.getNome(),
			prodotto.getIAP(),
			prodotto.getSerialNumber(),
			prodotto.getTipo(),
			prodotto.getMarca(),
			prodotto.getUtente(),
			prodotto.getScadenza(),
			prodotto.getCosto(),
			"abbonamento"
		);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		+ "abbonamento" + ","
		+ "null,"
		+ "null,"
		+ this.getUrl() + ","
		+ "null";
	}
	
	
}