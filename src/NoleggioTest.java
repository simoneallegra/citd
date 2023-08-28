import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class NoleggioTest {


    @Test
    public void testGiorniRimanenti(){
        Prodotto prodotto = new Prodotto("Nome", "IAP", "SN", "Tipo", "Marca", null, "null", 10, "TipoPossesso");
        Noleggio noleggio = new Noleggio(prodotto, null);
        
        //Test noleggio scaduto
        noleggio.setScadenza("14/04/2023");
        assertEquals("Prodotto Scaduto", false, noleggio.giorniRimanenti() > 0);

        //Test noleggio NON scaduto
        noleggio.setScadenza("14/04/2024");
        assertEquals("Prodotto NON Scaduto", true, noleggio.giorniRimanenti() > 0);
    }

    @Test
    public void testSetDocumentoNoleggio(){
        Prodotto prodotto = new Prodotto("Nome", "IAP", "SN", "Tipo", "Marca", null, "null", 10, "TipoPossesso");
        Noleggio noleggio = new Noleggio(prodotto, null);

        
        try {

            noleggio.setDocumentoNoleggio("C:/Users/simon/citd/citd/documents/contratto_di_noleggio_prova.pdf", "C:/Users/simon/citd/citd/documents");
            
            Path path = Paths.get( "C:/Users/simon/citd/citd/documents" + noleggio.getDocumentoNoleggio());
            assertFalse(Files.exists(path));

        } catch (IOException e) {
            //exception for read file
            assumeNoException(e);
        }
    }

    @Test
    public void testOpenDocumentoNoleggio(){
        Prodotto prodotto = new Prodotto("Nome", "IAP", "SN", "Tipo", "Marca", null, "null", 10, "TipoPossesso");
        Noleggio noleggio = new Noleggio(prodotto, null);

        
        try {

            noleggio.setDocumentoNoleggio("C:/Users/simon/citd/citd/documents/contratto_di_noleggio_prova.pdf", "C:/Users/simon/citd/citd/documents");
            
            int res = noleggio.openDocumentoNoleggio();
            assertEquals("Documento aperto", res, 1);

        } catch (IOException e) {
            //exception for read file
            assumeNoException(e);
        }
    }
}
