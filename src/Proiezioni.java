import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class Proiezioni {

    public Utils utility;

    public Proiezioni() {
		utility = new Utils();
	}

    public int proiezione(String possesso, String tipo, String costoMin,String costoMax, String impiegatoAssegnato, String date, String cadenza ) throws IOException, NumberFormatException{
        int proiezione_costo = 0;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data[][] = utility.getProductTableData(9);
        for(int i=0;i < data.length;i++){
            if(possesso.isEmpty() || possesso.equals(data[i][8])){
                if(tipo.isEmpty() || tipo.equals(data[i][3])){
                    if(( costoMin.isEmpty() || Integer.parseInt(costoMin) <= Integer.parseInt(data[i][7]) ) && ( costoMax.isEmpty() || Integer.parseInt(costoMax) >= Integer.parseInt(data[i][7]) )){
                        if(impiegatoAssegnato.isEmpty() || impiegatoAssegnato.equals(data[i][5])){
                            try {
                                if(date.isEmpty() || (data[i][6].equals("null") ? true : df.parse(data[i][6]).getTime() >= df.parse(date).getTime())){
                                    proiezione_costo += Integer.parseInt(data[i][7]);
                                }
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
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
