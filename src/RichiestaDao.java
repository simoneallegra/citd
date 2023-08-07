import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RichiestaDao{

	private List<Richiesta> richieste;
	
	public RichiestaDao(){
		richieste = new ArrayList<>();
	}

	
	public List<Richiesta> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_requests.txt"));
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
			PrintWriter writer = new PrintWriter("./database/db_requests.txt");
			
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
