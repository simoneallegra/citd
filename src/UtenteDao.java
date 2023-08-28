import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao{

	private List<Utente> utenti;
	private String db_path;
	
	public UtenteDao(String db_path){
		utenti = new ArrayList<>();
		this.db_path = db_path;
	}

	
	public List<Utente> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(db_path));
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
			PrintWriter writer = new PrintWriter(db_path);
			
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
