import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao{

	private List<Utente> utenti;
	
	public UtenteDao(){
		utenti = new ArrayList<>();
	}

	
	public List<Utente> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
			String s = "";
			while((s = br.readLine()) != null){
				String data[] = new String[6];
				data = s.split(",");
				utenti.add(new Utente(data[0], data[1], data[2], data[3], data[4], Boolean.valueOf(data[5])));
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return utenti;
	}

	public void set(List<Utente> newUtenti) {


		try {
			PrintWriter writer = new PrintWriter("./database/db_users.txt");
			
			String fields = "";
			for(Utente utente : newUtenti){
				fields += 
					utente.getMatricola() + "," 
					+ utente.getEncryptedPassword() + ","
					+ utente.getNome() + "," 
					+ utente.getCognome() + "," 
					+ utente.getEmail() + "," 
					+ utente.getIsSuperuser() +'\n';
			}
			
			writer.print(fields);
			writer.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

}
