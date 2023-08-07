import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
public class Proiezioni{

    public Utils utility;
    public List<Prodotto> prodotti;

    public Proiezioni(List<Prodotto> prodotti) {
		utility = new Utils();
        this.prodotti = prodotti;
	}

    public int proiezione(String possesso, String tipo, String costoMin,String costoMax, String impiegatoAssegnato, String date, String cadenza ) throws IOException, NumberFormatException{
        // TODO: sistemare questo pezzo di codice con la nuova politica ad oggetti        
        int proiezione_costo = 0;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data[][] = utility.listToMatrixString(this.prodotti);
        for(int i=0;i < data.length;i++){
            if(possesso.isEmpty() || possesso.equals(data[i][7])){
                if(tipo.isEmpty() || tipo.equals(data[i][2])){
                    if(( costoMin.isEmpty() || Integer.parseInt(costoMin) <= Integer.parseInt(data[i][6]) ) && ( costoMax.isEmpty() || Integer.parseInt(costoMax) >= Integer.parseInt(data[i][6]) )){
                        if(impiegatoAssegnato.isEmpty() || impiegatoAssegnato.equals(data[i][4])){
                            try {
                                if(date.isEmpty() || (data[i][5].equals("null") ? true : df.parse(data[i][5]).getTime() >= df.parse(date).getTime())){
                                    proiezione_costo += Integer.parseInt(data[i][6]);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        if(cadenza.equals("settimanale")){
            proiezione_costo = proiezione_costo/4;
        }else if(cadenza.equals("annuale")){
            proiezione_costo = proiezione_costo*12;
        }

        String filteredData[][] = new String[1][1];
        filteredData[0][0] = Integer.toString(proiezione_costo) + "\u20AC";
        
        return proiezione_costo;
		
	}
}
