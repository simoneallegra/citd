import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.crypto.Data;

public class Prodotto {

	private String nome;

	private String serial_number;

	private String IAP;

	private String tipo;

	private int barcode;

	private String marca;

	private String utente;
	
	private boolean inStock;

	private String scadenza;

	private int costo; //indica il costo mensile

	private String possesso;

	
	/**
	 *  
	 */
	public Prodotto(String nome, String serial_number, String IAP, String tipo, String marca, String utente, String scadenza, int costo, String possesso) {
		this.nome = nome;
		this.serial_number = serial_number;
		this.IAP = IAP;
		this.tipo = tipo;
		this.marca = marca;	
		this.utente= utente;
		this.scadenza= scadenza;
		this.costo= costo;
		this.possesso= possesso;
	}

	public Prodotto() {

	}
	
	public Prodotto(String nome, String serial_number) {
		this.nome = nome;
		this.serial_number = serial_number;
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4] + "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4] + "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8];
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
				campi[6] = scadenza;
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4] + "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4] + "," + campi[5]+ ","+campi[6]+ ","+campi[7];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4] + "," + campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8];
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
				riga[found] = campi[0] + "," + campi[1] + "," + campi[2] + "," +campi[3]+ ","+campi[4]+ ","+campi[5]+ ","+campi[6]+ ","+campi[7]+ ","+campi[8];
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
				String serial_number = data[1]; 
				if(name.equalsIgnoreCase(nome) || serial_number.equalsIgnoreCase(nome)){
					System.out.println("Product Found");
					product = new Prodotto(name, serial_number, data[2], data[3], data[4], data[5],data[6], Integer.parseInt(data[7]),data[8]);
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
					product = new Prodotto(name, iap, data[2], data[3], data[4], data[5],data[6], Integer.parseInt(data[7]),data[8]);
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

	public String aggiungiProdotto(String nome, String iap, String serial_number, String tipo, String marca) {
		try {
			String line = nome + "," + iap + "," + serial_number + "," + tipo + "," + marca + ",null"+ ",null" + ",0" + ",null";
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
	
}
