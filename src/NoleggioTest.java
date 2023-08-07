import static org.junit.Assert.*;
import org.junit.Test;

public class NoleggioTest {

    Noleggio noleggio = new Noleggio(null);
    int resp;

    @Test
    public void testGiorniRimanenti(){

        resp = noleggio.giorniRimanenti("01/01/2024");
        assertTrue(resp > 0);

        resp = noleggio.giorniRimanenti("01/01/2022");
         assertTrue(resp < 0);
    }

    @Test
    public void testOpenDocumento(){
        
        try {
            resp = noleggio.openDocumentoNoleggio("CITD006");
        } catch (Exception e) {
            resp = 1;
        }        
        System.out.println(resp);
        assertTrue(resp == 0);

        try {
            resp = noleggio.openDocumentoNoleggio("CITD004");
        } catch (Exception e) {
            resp = 1;
        }        
        assertTrue(resp == 1);
    }
}
