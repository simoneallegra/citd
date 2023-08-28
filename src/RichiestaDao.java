import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RichiestaDao{

	private List<Richiesta> richieste;
	private String db_path;

	public RichiestaDao(String db_path){
		richieste = new ArrayList<>();
		this.db_path = db_path;
	}

	
	public List<Richiesta> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(db_path));
			String s = "";
			while((s = br.readLine()) != null){
				String data[] = new String[2];
				data = s.split(",");
				richieste.add(new Richiesta(data[0], data[1]));
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return richieste;
	}

	public void set(List<Richiesta> newUtenti) {

		try {
			PrintWriter writer = new PrintWriter(db_path);
			
			String fields = "";
			for(Richiesta richiesta : richieste){
				fields += 
					richiesta.getIAP() + "," 
					+ richiesta.getRequest() + "\n";
			}
			
			writer.print(fields);
			writer.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

}
