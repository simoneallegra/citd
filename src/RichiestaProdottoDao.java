import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RichiestaProdottoDao{

	private List<RichiestaProdotto> richieste;
	
	public RichiestaProdottoDao(){
		richieste = new ArrayList<>();
	}

	
	public List<RichiestaProdotto> get(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_request_newproduct.txt"));
			String s = "";
			while((s = br.readLine()) != null){
				String data[] = new String[7];
				data = s.split(",");
				richieste.add(new RichiestaProdotto(data[0],data[1],data[2],data[3],data[4], data[5], data[6]));
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return richieste;
	}

	public void set(List<RichiestaProdotto> newUtenti) {

		try {
			PrintWriter writer = new PrintWriter("./database/db_request_newproduct.txt");
			
			String fields = "";
			for(RichiestaProdotto richiesta : richieste){
				fields += 
					richiesta.getIap() + "," +
					richiesta.getUtente() + "," +
					richiesta.getNomeProdotto() + "," +
					richiesta.getTipo() + "," +
					richiesta.getMarca() + "," +
					richiesta.getMotivo() + "," +
					richiesta.getStato() + "\n";
			}
			
			writer.print(fields);
			writer.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
