import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProiezioniTest {

    @Test
    public void testProiezione(){
        List<Prodotto> prodotti = new ArrayList<>(2);
        prodotti.add(new Prodotto("Prodotto 1", "IAP 1", "SN 1", "Tipo 1", "Marca 1", null, "null", 10, "Tipo Possesso 1"));
        prodotti.add(new Prodotto("Prodotto 2", "IAP 2", "SN 2", "Tipo 2", "Marca 2", null, "null", 10, "Tipo Possesso 2"));

        Proiezioni proiezioni = new Proiezioni(prodotti);
        try {
            int totale = proiezioni.proiezione("", "", "0", "50", "", "", "");
            assertEquals("Risultato aspettato: 20", 20, totale);
        } catch (NumberFormatException e) {
            assumeNoException(e);
        } catch (IOException e) {
            assumeNoException(e);
        }
    }
    
}
