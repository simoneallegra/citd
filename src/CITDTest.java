import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CITDTest{
 
     // ------------------ TEST UTENTI -----------------

    @Test
    public void testLogin(){

        Utente utente;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        
        utente = citd.Login("Invalid","Invalid");
        assertEquals("Credenziali non valide", utente, null);

        utente = citd.Login("","");
        assertNotEquals("Credenziali valide", utente, null);

    }

    @Test
    public void testGetDetailsUtente(){
    
        Utente utente;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );

        utente = citd.getDetailsUtente("Invalid");
        assertEquals("Utente non trovato", utente, null);

        utente = citd.getDetailsUtente("");
        assertNotEquals("Credenziali valide", utente, null);

    }

    @Test
    public void testInserisciNuovoUtente(){
    
        Utente utente;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaUtenti().size();

        utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.inserisciNuovoUtente(utente);
        assertEquals("Utente aggiunto", true, citd.getListaUtenti().size() > size_before);

    }

    @Test
    public void testEliminaUtente(){
    
        Utente utente;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaUtenti().size();

        utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.inserisciNuovoUtente(utente);

        citd.eliminaUtente("matricola");
        assertEquals("Utente aggiunto ed eliminato", true, citd.getListaUtenti().size() == size_before);

    }

    @Test
    public void testUpdateUtente(){
    
        Utente utente;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaUtenti().size();

        utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.inserisciNuovoUtente(utente);

        citd.updateUtente("matricola","matricola2", "password", "nome", "cognome", "email", false);
        assertEquals("Matricola utente modificata", true, citd.getDetailsUtente("matricola2") != null);

    }

    // ------------------ TEST PRODOTTI -----------------

    @Test
    public void testGetDetailsProdotto(){
    
        Prodotto prodotto;
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");

        prodotto = citd.getDetailsProdotto("Invalid");
        assertEquals("Prodotto NON trovato", prodotto, null);

        prodotto = citd.getDetailsProdotto("IAP");
        assertNotEquals("Prodotto trovato", prodotto, null);

    }
    
    @Test
    public void testAggiungiProdotto(){
    
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaProdotti().size();

        citd.aggiungiProdotto("Nome", "IAP1", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");
        assertEquals("Prodotto Acquistato", true, citd.getDetailsProdotto("IAP1") instanceof Prodotto);

        citd.aggiungiProdotto("Nome", "IAP2", "SN", "Tipo", "Marca",null,"abbonamento", 10, "01/01/2024","678.pdf");
        assertEquals("Prodotto in Abbonamento", true, citd.getDetailsProdotto("IAP2") instanceof Abbonamento);

        citd.aggiungiProdotto("Nome", "IAP3", "SN", "Tipo", "Marca",null,"noleggio", 10, "01/01/2024","");
        assertEquals("Prodotto Noleggiato", true, citd.getDetailsProdotto("IAP3") instanceof Noleggio);

        assertEquals("Prodotti aggiunti", true, citd.getListaProdotti().size() == size_before + 3);

    }

    @Test
    public void testEliminaProdotto(){
    
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaProdotti().size();

        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");

        citd.eliminaProdotto("IAP");

        assertEquals("Prodotti aggiunto ed eliminato", true, citd.getListaProdotti().size() == size_before);

    }

    @Test
    public void testModificaProdotto(){
    
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        int size_before = citd.getListaProdotti().size();

        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");

        citd.modificaProdotto("IAP", "nome", "IAP1", "SN", "tipo", "marca");
        assertEquals("Prodotto modificato", true, citd.getDetailsProdotto("IAP1") != null);

    }

    @Test
    public void testSetManutenzione(){
    
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");
        int size_before = citd.getListaManutenzione().size();
        citd.setManutenzione("IAP", "lavorazione");
        assertEquals("Manutenzione attivata", true, citd.getListaManutenzione().size() > size_before);
    }

    @Test
    public void testDeleteMaintenanceRequest(){
    
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");
        
        citd.setProblemRequest("IAP", "problema");
        citd.deleteMaintenanceRequest("IAP");
        assertEquals("Aggiunta e rimozione richiesta manutenzione", true, citd.getRichiesta("IAP") == null);
    }

    @Test
    public void testGetUserProduct(){
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );
        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",citd.getDetailsUtente(""),"manutenzione", 10, "01/01/2024","");
        String[][] stringa = citd.getUserProduct("IAP");
        System.out.println(stringa[0][0]);
        // assertEquals("Get utente-prodotto", true, stringa[0][0] == "nome");
        assertEquals("Get utente-prodotto", true, stringa[0][0] != null);
    }

    @Test
    public void testAssegnaUtente(){
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );

        citd.aggiungiProdotto("Nome", "IAP", "SN", "Tipo", "Marca",null,"acquisto", 10, "01/01/2024","");
        Utente utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.inserisciNuovoUtente(utente);

        citd.assegnaUtente(citd.getDetailsProdotto("IAP"), "matricola");

        assertEquals("Utente", true, citd.getDetailsProdotto("IAP").getUtente().getMatricola() == "matricola");
    }

    @Test
    public void testAggiungiRichiestaNuovoProdotto(){
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );

        int size_before = citd.getAllRichiesteProdotto().size();

        Utente utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.aggiungiRichiestaNuovoProdotto("IAP", "nome", "tipo", "marca", "descrizione", utente.getNome());
        citd.getListaRichiestaNuovoProdotto(true, "matricola");
        
        // assertEquals("Get utente-prodotto", true, stringa[0][0] == "nome");
        assertEquals("Aggiungi richiesta prodotto", true, citd.getAllRichiesteProdotto().size() > size_before);
    }

    @Test
    public void testDeleteNewProductRequest(){
        CITD citd = new CITD(
            "C:\\Users\\simon\\citd\\citd\\database\\db_users.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_products.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_requests.txt",
            "C:\\Users\\simon\\citd\\citd\\database\\db_request_newproduct.txt"
        );

        int size_before = citd.getAllRichiesteProdotto().size();

        Utente utente = new Utente("matricola", "password", "nome", "cognome", "email", false);
        citd.aggiungiRichiestaNuovoProdotto("IAP", "nome", "tipo", "marca", "descrizione", utente.getNome());
        citd.getListaRichiestaNuovoProdotto(true, "matricola");
        
        assertEquals("Aggiungi richiesta prodotto", true, citd.getAllRichiesteProdotto().size() > size_before);

        citd.deleteNewProductRequest("IAP");
        assertEquals("Remove richiesta prodotto", true, citd.getAllRichiesteProdotto().size() == size_before);
    }
}
