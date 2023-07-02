import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Manutenzione extends Prodotto{

	
	public Manutenzione() {
		
	}
	
	public String [][] getMaintenanceProduct() {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			int i = 0;
			int counter = 0;
			ArrayList <String[]>listOfArrays = new ArrayList<>();
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				if(split[9].equalsIgnoreCase("manutenzione") && !split[10].equalsIgnoreCase("rifiutata")) {
					String dati[] =  new String[3];
					counter++;
					dati[0] = split[0];
					dati[1] = split[1];
					dati[2] = split[10];
					listOfArrays.add(dati);
				}	
				i++;
			}
			int riga=0;
			data = new String[counter][3];
	        // Copia gli elementi non nulli nella nuova matrice
	        for (String[] array : listOfArrays) {
				int column=0;
	            for (String element : array) {
	            	data[riga][column]= element;
	            	column++;
	            }
	            riga++;
	        }
			br.close();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public void acceptMaintenance(String iap, String stato, Boolean request) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				int i =0;				
				int found=0;
			while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[1].equalsIgnoreCase(iap)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[10] = stato;
				if(request) {
					campi[9] ="manutenzione";					
				}
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
			}			
	        FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(file);
			br.close();
	        bw.flush();
	        bw.close();

		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}
	
	public void setProblemRequest(String iap, String problema) {
		try {
			String richiesta = iap + "," + problema;
	        FileWriter fw = new FileWriter("./database/db_requests.txt", true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(richiesta);
	        bw.write("\n");
	        bw.flush();
	        bw.close();			
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());			
		}catch(Exception e) {
			System.out.println(e.getMessage());			
		}
	}
	
	public String[][] getCareRequest(String iap){
		String dati[][] = new String[1][2];
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_requests.txt"));
			String s = "";
			int i = 0;
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				if(split[0].equalsIgnoreCase(iap)) {
					dati[0][0] = split[0];
					dati[0][1] = split[1];
				}	
				i++;
			}
			br.close();
			return dati;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
}