import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class RichiestaProdotto{

	private String iap;
	private String nome;
	private String tipo;
	private String marca;
	private String utente;
	private String dettagli;
	private String stato;
	private Utils utility = new Utils();

	public RichiestaProdotto() {
		
	}
	
	
	
	public String getIap() {
		return iap;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getMarca() {
		return marca;
	}

	public String getUtente() {
		return utente;
	}

	public String getDettagli() {
		return dettagli;
	}

	public String getStato() {
		return stato;
	}

	
	public RichiestaProdotto(String iap) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_request_newproduct.txt"));
			String s= "";
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				if(data[0].equalsIgnoreCase(iap)) {
					this.iap=data[0];
					this.nome=data[1];
					this.tipo=data[2];
					this.marca=data[3];
					this.dettagli=data[4];
					this.stato=data[5];
					this.utente=data[6];
					break;
				}
			}

		}catch(Exception e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	public String aggiungiRichiesta(String nome, String tipo, String marca, String dettaglio, String utente){	
		try {
			//processo per creare una sorta di id autoincrement per le richieste. N.B. se la richiesta viene approvata, comunque l'iap viene modificato  
			BufferedReader br = new BufferedReader(new FileReader("./database/db_request_newproduct.txt"));
			String iap = utility.getIap(br, 0);
			String richiesta = iap + "," + nome + "," + tipo + "," + marca + "," + dettaglio + "," + "In attesa di approvazione," + utente;
	        FileWriter fw = new FileWriter("./database/db_request_newproduct.txt", true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(richiesta);
	        bw.write("\n");
	        bw.flush();
	        bw.close();
	        return "Inserito in db";
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}catch(IOException e) {
			System.out.println(e.getMessage());	
			return null;
		}catch(Exception e) {
			System.out.println(e.getMessage());	
			return null;
		}
	}

	public String [][] visualizza(Utente utente) {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_request_newproduct.txt"));
			String s = "";
			//int i = 0;
			int counter = 0;
			ArrayList <String[]>listOfArrays = new ArrayList<>();
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				if(!utente.superuser) {
					if(split[6].equalsIgnoreCase(utente.matricola)) {
						String dati[] =  new String[6];
						counter++;
						dati[0] = split[1];
						dati[1] = split[2];
						dati[2] = split[3];
						dati[3] = split[4];
						dati[4] = split[5];											
						listOfArrays.add(dati);
					}	
				}else {
					if(split[5].equalsIgnoreCase("In attesa di approvazione")) {
						String dati[] =  new String[7];
						counter++;
						dati[0] = split[0];
						dati[1] = split[1];
						dati[2] = split[2];
						dati[3] = split[3];
						dati[4] = split[4];
						dati[5] = split[6];
						dati[6] = split[5];
						listOfArrays.add(dati);
					}
				}
			}
			int riga=0;
			data = new String[counter][7];
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
	
	public void editRichiesta(String iap, String esito) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_request_newproduct.txt"));
			String s = "";
			String file="";
			String result ="";
			int i =0;
			int found=0;
			while((s = br.readLine()) != null){
				result = result + s + "\n";
				String data[] = s.split(",");
				 if(data[0].equalsIgnoreCase(iap)) {
					found=i; 
				 }
			     i++;
			}
			String riga[] = result.split("\n");
			String campi[] = riga[found].split(",");
			//cambiare indice ad ogni set
			campi[5] = esito;
			riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6];
			for(int j=0; j<riga.length; j++) {
				file = file + riga[j] + "\n";
			}
	        FileWriter fw = new FileWriter("./database/db_request_newproduct.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(file);
			br.close();
	        bw.flush();
	        bw.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
}