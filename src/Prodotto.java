import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

public class Prodotto {

	private String nome;

	private String serial_number;

	private String IAP;

	private String tipo;

	private String marca;

	private String utente;
	
	private String scadenza;

	private int costo; //indica il costo mensile

	private String possesso;
		
	private String manutenzione; //indica se il prodotto è in manutenzione

	private String stato;
	
	private String url;//per le licenze software

	private String documents;
	
	private Utils utility = new Utils();
	
	/**
	 *  
	 */
	public Prodotto(String nome, String serial_number, String tipo, String marca, String utente, String scadenza, int costo, String possesso, String manutenzione, String stato) {
		this.nome = nome;
		this.serial_number = serial_number;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente= utente;
		this.scadenza= scadenza;
		this.costo= costo;
		this.possesso= possesso;
		this.manutenzione= manutenzione;
		this.stato= stato;
	}

	public Prodotto() {

	}
	
	public Prodotto(String nome, String serial_number) {
		this.nome = nome;
		this.serial_number = serial_number;
	}
	
	public Prodotto(String nome, String serial_number, String tipo, String marca, String tipologia_acquisto, int costo, String scadenza, String link ) {
		this.nome = nome;
		//this.IAP = iap;
		this.serial_number = serial_number;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente= "null";
		this.scadenza= scadenza;
		this.costo= costo;	
		this.possesso=tipologia_acquisto;
		this.manutenzione= "funzionante";
		this.stato= "null";
		this.url =link;
		this.documents = "null";
	}
	
	public Prodotto(String nome, String serial_number, String tipo, String marca, String utente, String tipologia_acquisto, int costo, String scadenza, String link ) {
		this.nome = nome;
		//this.IAP = iap;
		this.serial_number = serial_number;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente= utente;
		this.scadenza= scadenza;
		this.costo= costo;	
		this.possesso=tipologia_acquisto;
		this.manutenzione= "funzionante";
		this.stato= "null";
		this.url =link;
		this.documents = "null";
	}

	public Prodotto(String nome, String iap, String serial_number, String tipo, String marca,String utente, String scadenza, int costo, String tipologia_acquisto, String manutenzione, String stato, String link, String documents ) {
		this.nome = nome;
		this.IAP = iap;
		this.serial_number = serial_number;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente= utente;
		this.scadenza= scadenza;
		this.costo= costo;	
		this.possesso=tipologia_acquisto;
		this.manutenzione= manutenzione;
		this.stato= stato;
		this.url =link;
		this.documents = documents;
	}

	public Prodotto(String iap) {
		this.IAP = iap;
	}

	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + ", serial_number=" + serial_number + "]";
	}

	public String getNome() {
		return this.nome;
	}

	public String getSerialNumber() {
		return this.serial_number;
	}

	public String getIAP() {
		return this.IAP;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public String getMarca() {
		return this.marca;
	}

	public String getUtente() {
		return this.utente;
	}
	
	public String getScadenza() {
		return this.scadenza;
	}

	public int getCosto() {
		return this.costo;
	}

	public String getPossessoa() {
		return this.possesso;
	}

	public void setNome(String nome) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

			while((s = br.readLine()) != null){
					result = result + s + "\n";
				String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[0] = nome;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
			}			
	        FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
			br.close();
	        bw.flush();
	        bw.close();
				
			this.nome= nome;

		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}

	public void setSerialNumber(String serialnumber) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[1] = serialnumber;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
	        bw.flush();
	        bw.close();
				this.serial_number= serialnumber;

		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}
	
	public void setIAP(String iap) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[2] = iap;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.IAP = iap;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
		
	}
	
	public void setTipo(String tipo) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[3] = tipo;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.tipo = tipo;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}
	
	public void setMarca(String marca) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[4] = marca;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.marca = marca;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}
	
	public void setUtente(String utente) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[5] = utente;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.utente = utente;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}

	public void setScadenza(String scadenza) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String iap=this.IAP; 
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
				campi[6] = scadenza;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.scadenza = scadenza;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}

	public void setCosto(String costo) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[7] = costo;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.costo = Integer.parseInt(costo);
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}

	public void setPossesso(String possesso) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;

				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[8] = possesso;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.possesso = possesso;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}
	
	public void setProdotto(String nome, String iap, String numero, String tipo, String marca) {
		 try {
				BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
				String s, file = "";
				String result ="";
				String originalname=this.nome; 
				int i =0;
				int found=0;
				while((s = br.readLine()) != null){
					result = result + s + "\n";
					String data[] = s.split(",");
					 if(data[0].equalsIgnoreCase(originalname)) {
						found=i; 
					 }
				     i++;
				}
				String riga[] = result.split("\n");
				String campi[] = riga[found].split(",");
				//cambiare indice ad ogni set
				campi[0]=nome;
				campi[1]=iap;
				campi[2]=numero;
				campi[3]=tipo;
				campi[4] = marca;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10]+ ","+campi[11]+ ","+campi[12];
				for(int j=0; j<riga.length; j++) {
					file = file + riga[j] + "\n";
				}
		        FileWriter fw = new FileWriter("./database/db_product.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
				bw.write(file);
				br.close();
		        bw.flush();
		        bw.close();
				this.marca = marca;
		 }catch(Exception e) {
			System.out.println(e.getMessage()); 
		 }
	}


	
	public Prodotto visualizza(String nome) {	
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			Prodotto product = null;
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String name = data[0];
				String iap = data[1]; 
				//nome       iap     serialN   tipo   marca   utente  data   costo  tipologia inmanutenzione  stato  link     documenti

				if(name.equalsIgnoreCase(nome) || iap.equalsIgnoreCase(nome)){
					System.out.println("Product Found");
					product = new Prodotto(name, iap, data[2], data[3], data[4], data[5],data[6], Integer.parseInt(data[7]),data[8], data[9], data[10], data[11],data[12]);
					br.close();
					return product;
				}
			}
			System.out.println("Product Not Found");
			br.close();
			return null;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public String destroy(String iap) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s,prova = "";
			Boolean found = false;
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String nome = data[0];
				String iap_db = data[1];
				if(!iap_db.equalsIgnoreCase(iap) && !nome.equalsIgnoreCase(iap)){
			        prova = prova + s + '\n';
			    }else {
					found = true;
				}
			}			
	        FileWriter fw = new FileWriter("./database/db_product.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(prova);
			br.close();
	        bw.flush();
	        bw.close();
	        if(found) {
				return "Prodotto eliminato dal DB";
			}else {
				return "Prodotto non presente in DB";				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Prodotto edit(String prodotto) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			Prodotto product = null;
			while((s = br.readLine()) != null){
				String data[] = s.split(",");
				String name = data[0];
				String iap = data[1]; 
				if(name.equalsIgnoreCase(prodotto) || iap.equalsIgnoreCase(prodotto)){
					System.out.println("Product Found");
					product = new Prodotto(name, iap, data[2], data[3], data[4], data[5],data[6], Integer.parseInt(data[7]),data[8], data[9], data[10], data[11], data[12]);
					br.close();
					return product;
				}else {
					System.out.println("Product not found");
				}
			}
			br.close();
			return product;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String aggiungiProdotto(Prodotto prod) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String iap = utility.getIap(br, 1);
			if(iap != null) {
				this.IAP=iap;
			}
			//CAMPI_DB: nome,iap,serialnumber,tipo,marca,utente,scadenza,costo,Tipo_acquisto,manutenzione,stato_manutenzione,link,documento
			String line = prod.nome + "," + iap+ "," + prod.serial_number + "," 
						+ prod.tipo + "," + prod.marca + "," + prod.utente + "," 
						+ prod.scadenza + "," + prod.costo + "," + prod.possesso + "," 
						+ prod.manutenzione + "," + prod.stato + "," + prod.url + "," + prod.documents;
	        FileWriter fw = new FileWriter("./database/db_product.txt", true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(line);
	        bw.write("\n");
	        bw.flush();
	        bw.close();
            return "Prodotto aggiunto al DB";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public String [][] getLicense() {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			int i = 0;
			int counter = 0;
			ArrayList <String[]>listOfArrays = new ArrayList<>();
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				String scadenza = split[6];
				int risultato = 0;
				if(!scadenza.equalsIgnoreCase("null")) {			
			        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			        Date scad=null;
			        try {
			            scad = formatoData.parse(scadenza);
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
			        Date dataOggi = new Date();
			        risultato = scad.compareTo(dataOggi);
				}
		        //se risultato è minore di 0, la scadenza è precedente alla data di oggi
				if(split[3].equalsIgnoreCase("software") && risultato<0 ) {
					String dati[] =  new String[4];
					counter++;
					dati[0] = split[0];
					dati[1] = split[1];
					dati[2] = split[4];
					dati[3] = split[6];
					listOfArrays.add(dati);
				}	
				i++;
			}
			int riga=0;
			data = new String[counter][4];
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
	
	public String getUrl(String iap) {
		try {
		BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
		String s = "";
		String url="";
		int i = 0;
		while((s = br.readLine()) != null){
			String data[] = s.split(",");
			if(data[1].equalsIgnoreCase(iap)) {
				url = data[11];
				br.close();
				return url;
			}
			i++;
		}
		br.close();
		return null;
		
		}catch(FileNotFoundException e) {
			return null;
		}catch(IOException e) {
			return null;			
		}
	}
	
	
	public String rinnovaScadenza(String iap, String scadenza) {
		 try {
			 int risultato=0;
		        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		        try {
		            Date data = formatoData.parse(scadenza);
			        Date dataOggi = new Date();
			        risultato = data.compareTo(dataOggi);
			        if(risultato<0) {
			        	//errore da gestire
			        	return null;
			        }
		        } catch (ParseException e) {
					e.getMessage();
		        	return null;
		        }
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+scadenza+ ","+campi[7]+ ","+campi[8]+ ","+campi[9]+ ","+campi[10] + "," + campi[11]+ ","+campi[12];
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
			return null;
		 }
	        return "ok";
	}

	public String [][]getUserProduct(String matricola) {
		String data[][] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
			String s = "";
			int i = 0;
			int counter = 0;
			ArrayList <String[]>listOfArrays = new ArrayList<>();
			while((s = br.readLine()) != null){
				String split[]	= s.split(",");
				String utente = split[5];
				if((utente.equalsIgnoreCase(matricola) || utente.equalsIgnoreCase("null")) && !split[3].equalsIgnoreCase("software") ) {
					String dati[] =  new String[4];
					counter++;
					dati[0] = split[0];
					dati[1] = split[1];
					dati[2] = split[9];
					dati[3] = split[10];
					if(dati[3].equalsIgnoreCase("null")) {
						dati[3]="-";
					}
					listOfArrays.add(dati);
				}	
				i++;
			}
			int riga=0;
			data = new String[counter][4];
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
	
	
}
