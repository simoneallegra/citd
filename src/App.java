import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.text.AbstractDocument.LeafElement;


public class App {
	
	static CardLayout cl;
	
	private JFrame frame;
	private JTable jt, jtProduct;
	private JPanel mainPanel, homePanel, loginPanel, userSetupPanel, userAddPanel, productSetupPanel, researchProductPanel, addProductPanel, viewProductPanel,tableProductPanel, editProductPanel, userPanel;
	private JLabel matricolaLabel, passwordLabel,addLabel, productLabel, addProductLabel,researchErrorLabel, iapProductLabel;
	private JTextField matricolaField, passwordField, addProductField, serialNumberProductField, researchProductField,iapProductField,typeProductField,brandProductField, nameProductField, numberProductField, editIapProductField,editTypeProductField,editBrandProductField,nomeField,cognomeField,emailField;
	private JRadioButton superuserField;
	private JButton loginBtn,backBtn ,searchProductButton, addProductButton, researchProductButton, deleteProductBtn,  addBtn, editBtn, deleteBtn, editProductBtn, viewProductButton, saveBtn,resetProductTableButton;
	private JMenuBar homeBar;
	private JMenu setupMenu,setupMenuLogout;
	private JMenuItem itemUserSetup,itemProductSetup,itemLogoutSetup;
	private JTextArea text;

	String valueInCell = "";
	String[] arrayString;
	int pressButton=0;
	Boolean found= false;
	Utente userLogged = null;
	
	public CITD citd;
	
	public Utils utility;

	
	public App() {	
		initialize();
	}

	
	private void initialize() {
		
		utility = new Utils();
		cl = new CardLayout();
		
		mainPanel = new JPanel(cl);
				
		frame = new JFrame("CITD");
		frame.setBounds(600,300,980,720);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		loginPanel = new JPanel();
		loginPanel.setLayout(null);

		matricolaLabel = new JLabel("matricola");
		matricolaLabel.setBounds(350, 250, 70, 15);
		loginPanel.add(matricolaLabel);
		
		matricolaField = new JTextField();
		matricolaField.setBounds(420, 250, 144, 20);
		loginPanel.add(matricolaField);
		matricolaField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setBounds(350, 280, 70, 15);
		loginPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(420, 280, 144, 20);
		loginPanel.add(passwordField);
		passwordField.setColumns(10);
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(matricolaField.getText() +" "+ passwordField.getText());
				
				citd = new CITD();
				userLogged = citd.Login(matricolaField.getText(), passwordField.getText());
				//frame.getContentPane().remove(loginPanel);
				if(userLogged != null) {
					System.out.println(userLogged.superuser);
					matricolaField.setText("");
					passwordField.setText("");
					home(userLogged);
				}				
			}
		});
		loginBtn.setBounds(420, 310, 144, 20);
		loginPanel.add(loginBtn);
		
		mainPanel.add(loginPanel,"loginPanel");
        frame.add(mainPanel);
        cl.show(mainPanel, "loginPanel");
		
	}
	
	private void home(Utente utente){
		try {
			homePanel = new JPanel();
			
			homeBar = new JMenuBar();
			frame.setJMenuBar(homeBar);
			setupMenu = new JMenu("Gestione");
			
			homeBar.add(setupMenu);
			mainPanel.add(homePanel,"homePanel");
			cl.show(mainPanel, "homePanel");
			itemUserSetup = new JMenuItem("Gestione Utenti");
			itemUserSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					userSetupPanel = new JPanel();
					userSetupPanel.setLayout(new BoxLayout(userSetupPanel, BoxLayout.Y_AXIS));
					
				    userAddPanel = new JPanel(new GridLayout(4,3,5,25));
				    userAddPanel.setAlignmentX( Component.RIGHT_ALIGNMENT );
					
					addLabel = new JLabel("Matricola");
					//addLabel.setBounds(10, 10, 70, 15);
					userAddPanel.add(addLabel);
					
					matricolaField = new JTextField();
					//matricolaField.setBounds(80, 10, 144, 20);
					userAddPanel.add(matricolaField);
					matricolaField.setColumns(10);
					
					addLabel = new JLabel("Password");
					//addLabel.setBounds(10, 40, 70, 15);
					userAddPanel.add(addLabel);
					
					passwordField = new JTextField();
					//passwordField.setBounds(80, 40, 144, 20);
					userAddPanel.add(passwordField);
					passwordField.setColumns(10);

					addLabel = new JLabel("Nome");
					addLabel.setBounds(10, 49, 65, 14);
					userAddPanel.add(addLabel);
					
					nomeField = new JTextField();
					nomeField.setBounds(85, 46, 144, 20);
					userAddPanel.add(nomeField);
					nomeField.setColumns(10);

					addLabel = new JLabel("Cognome");
					addLabel.setBounds(10, 69, 65, 14);
					userAddPanel.add(addLabel);
					
					cognomeField = new JTextField();
					cognomeField.setBounds(85, 66, 144, 20);
					userAddPanel.add(cognomeField);
					cognomeField.setColumns(10);

					addLabel = new JLabel("Email");
					addLabel.setBounds(10, 89, 65, 14);
					userAddPanel.add(addLabel);
					
					emailField = new JTextField();
					emailField.setBounds(85, 86, 144, 20);
					userAddPanel.add(emailField);
					emailField.setColumns(10);

					addLabel = new JLabel("Amministratore");
					addLabel.setBounds(10, 109, 150, 14);
					userAddPanel.add(addLabel);
					
					superuserField = new JRadioButton();
					superuserField.setBounds(150, 106, 144, 20);
					userAddPanel.add(superuserField);
					// superuserField.setColumns(10);
					
					addBtn = new JButton("Aggiungi");
					
					addBtn.setBounds(80, 70, 144, 20);

					userAddPanel.add(addBtn);
					userSetupPanel.add(userAddPanel);
					
					mainPanel.add(userSetupPanel, "userSetupPanel");
					cl.show(mainPanel, "userSetupPanel");
					
					
					try {
						userPanel = new JPanel();
				    	userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
						
						BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
						int lines = 0;
						while (br.readLine() != null) lines++;
											
						String data[][] = new String[lines][6];
						br.close();
						
						br = new BufferedReader(new FileReader("./database/db_users.txt"));
						String s = "";
						int i = 0;
						while((s = br.readLine()) != null){
							
							data[i] = s.split(",");
							i++;
						}
						
						br.close();
						
						String column[]={"USERNAME","PASSWORD","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
						jt = new JTable(data,column); 
					    userPanel.add(new JScrollPane(jt));
					    
					    jt.addMouseListener(new MouseAdapter() {
					        @Override
					        public void mouseClicked(final MouseEvent e){
					            if (e.getClickCount() == 1){
					                final JTable jTable= (JTable)e.getSource();
					                final int row = jTable.getSelectedRow();

									arrayString = new String[jTable.getColumnCount()];
									int i = 0;
									while (i<jTable.getColumnCount()){
										arrayString[i] = (String)jTable.getValueAt(row, i);
										i++;
									}
					                
					            }
					        }});
					    
						addBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println(passwordField.getText());
							Utente newUser = new Utente(matricolaField.getText(), passwordField.getText(), nomeField.getText(), cognomeField.getText(), emailField.getText(), superuserField.isSelected());
							System.out.println(newUser.nome);
							citd.inserisciNuovoUtente(newUser);
							
							try {
										userPanel = new JPanel();
										userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
										
										BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
										int lines = 0;
										while (br.readLine() != null) lines++;
															
										String data[][] = new String[lines][6];
										br.close();
										
										br = new BufferedReader(new FileReader("./database/db_users.txt"));
										String s = "";
										int i = 0;
										while((s = br.readLine()) != null){
											
											data[i] = s.split(",");
											i++;
										}
										
										br.close();
										
										String column[]={"USERNAME","PASSWORD","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
										Model table = new Model(data,column);
										jt.setModel(table);
										((Model) jt.getModel()).fireTableDataChanged();
									}
									catch(Exception e){

									}
							
						}
					});

					    editBtn = new JButton("Edit");
					    editBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println(arrayString[0]);
								if(!arrayString.equals(null)) {
																		
									userSetupPanel = new JPanel();
									userSetupPanel.setLayout(new BoxLayout(userSetupPanel, BoxLayout.Y_AXIS));
									//userSetupPanel.setPreferredSize(new Dimension(200, 200));
									
									backBtn = new JButton("Back");
									backBtn.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											cl.show(mainPanel, "userSetupPanel");
										}
									});
									backBtn.setBounds(85, 66, 72, 20);
					    			userSetupPanel.add(backBtn);

									addLabel = new JLabel("Matricola");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);

									matricolaField = new JTextField();
									matricolaField.setBounds(85, 6, 144, 20);
									userSetupPanel.add(matricolaField);
									matricolaField.setColumns(10);
									matricolaField.setText(arrayString[0]);

									addLabel = new JLabel("Password");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);
									
									passwordField = new JTextField();
									passwordField.setBounds(85, 26, 144, 20);
									userSetupPanel.add(passwordField);
									passwordField.setColumns(10);

									addLabel = new JLabel("Nome");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);
									
									nomeField = new JTextField();
									nomeField.setBounds(85, 46, 144, 20);
									userSetupPanel.add(nomeField);
									nomeField.setColumns(10);
									nomeField.setText(arrayString[2]);

									addLabel = new JLabel("Cognome");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);
									
									cognomeField = new JTextField();
									cognomeField.setBounds(85, 66, 144, 20);
									userSetupPanel.add(cognomeField);
									cognomeField.setColumns(10);
									cognomeField.setText(arrayString[3]);

									addLabel = new JLabel("Email");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);
									
									
									emailField = new JTextField();
									emailField.setBounds(85, 86, 144, 20);
									userSetupPanel.add(emailField);
									emailField.setColumns(10);
									emailField.setText(arrayString[4]);

									addLabel = new JLabel("Amministratore");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);
									
									superuserField = new JRadioButton();
									superuserField.setBounds(150, 106, 144, 20);
									userSetupPanel.add(superuserField);									
									superuserField.setSelected(Boolean.valueOf(arrayString[5]));
									
									//da sostituire con l'utente ottenuto in fase di return details
									
									saveBtn = new JButton("Save");
									saveBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												System.out.println("password:"+passwordField.getText());
												Boolean passwordUpdate = true;
												String passwordPassed = "";
												if(passwordField.getText().equalsIgnoreCase("")){
													passwordPassed = arrayString[1];
													passwordUpdate = false;
												}else{
													passwordPassed = passwordField.getText();
												}


												citd.updateUtente(arrayString[0], passwordUpdate, new Utente(matricolaField.getText(),passwordPassed,nomeField.getText(),cognomeField.getText(),emailField.getText(),superuserField.isSelected()));
											}});
									saveBtn.setBounds(85, 66, 72, 20);
								    userSetupPanel.add(saveBtn);
								    
								    mainPanel.add(userSetupPanel, "userSetupPanelEdit");
									cl.show(mainPanel, "userSetupPanelEdit");
								}
							}
						});
					    editBtn.setBounds(85, 66, 72, 20);
					    userPanel.add(editBtn);
						
					    deleteBtn = new JButton("Delete");
					    deleteBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if(arrayString[0] != "") {
																	
									//frame.getContentPane().remove(userSetupPanel);
									citd.eliminaUtente(arrayString[0].toString());
									
									try {
										userPanel = new JPanel();
										userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
										
										BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
										int lines = 0;
										while (br.readLine() != null) lines++;
															
										String data[][] = new String[lines][6];
										br.close();
										
										br = new BufferedReader(new FileReader("./database/db_users.txt"));
										String s = "";
										int i = 0;
										while((s = br.readLine()) != null){
											
											data[i] = s.split(",");
											i++;
										}
										
										br.close();
										
										String column[]={"USERNAME","PASSWORD","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
										Model table = new Model(data,column);
										jt.setModel(table);
										((Model) jt.getModel()).fireTableDataChanged();
									}
									catch(Exception e){

									}
								}
							}
						});
					    deleteBtn.setBounds(85, 86, 72, 20);
					    userPanel.add(deleteBtn);
						userSetupPanel.add(userPanel);
					}catch(Exception e) {
						System.out.println(e);
					}
					
				}
			});
			if(userLogged.superuser) setupMenu.add(itemUserSetup);

			itemProductSetup = new JMenuItem("Gestione Prodotti");
			itemProductSetup.setLayout(new BorderLayout());
			setupMenu.add(itemProductSetup);
			itemProductSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					productSetupPanel = new JPanel();
					//All'interno del panel inserisco un tris di bottoni
					//RICERCA PRODOTTO, AGGIUNTA ED ELIMINAZIONE
					// searchProductButton = new JButton("Cerca Prodotto");
					// productSetupPanel.add(searchProductButton);
					
					addProductButton = new JButton("Aggiungi Prodotto");
					productSetupPanel.add(addProductButton);
						
					viewProductButton = new JButton("Visualizza Prodotti");
					productSetupPanel.add(viewProductButton);
				
					if(userLogged != null && userLogged.superuser == false ) {
						addProductButton.setVisible(false);
						// viewProductButton.setVisible(false);
					}

					mainPanel.add(productSetupPanel, "productSetupPanel");
					cl.show(mainPanel, "productSetupPanel");
					
					//evento ricerca prodotto
					/*searchProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							researchProductPanel = new JPanel();

							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}});
							backBtn.setBounds(85, 66, 72, 20);
							researchProductPanel.add(backBtn);
														
							productLabel = new JLabel("Nome Prodotto");
							//productLabel.setBounds(10, 9, 65, 14);
							researchProductPanel.add(productLabel);
							
							researchProductField = new JTextField();
							researchProductPanel.add(researchProductField);
							researchProductField.setColumns(10);
							
							researchProductButton = new JButton("Cerca");
							researchProductPanel.add(researchProductButton);
							researchProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if (pressButton == 0 || !found) {
										pressButton++;
									}else {
										text.setText("");
										researchProductPanel.remove(text);										
									}

									// citd = new CITD();
									Prodotto product = citd.visualizzaProdotto(researchProductField.getText());
									if (product != null) {
										found = true;
										System.out.println("Il prodotto che ho trovato e':" + product);
										text = new JTextArea();
										// String colonna[]={"NOME","NUMERO SERIALE"};
										// String data[][] = new String[1][2];
										// data[0][0] = product.getNome();
										// data[0][1] = product.getNome();
										// System.out.println("data è :" +data[0]);
										// jtProduct = new JTable(data,colonna);
										// researchProductPanel.add(new JScrollPane(jtProduct));

										researchProductPanel.add(text);
										String textarea = "Prodotto trovato:\n Nome: " + product.getNome() + "\n Numero seriale: " + product.getSerialNumber() +
														"\n IAP: " + product.getIAP() + "\n Tipo: " + product.getTipo() + "\n Marca: " + product.getMarca();
										text.setText(textarea);
									}else {
										found=false;
										//researchErrorLabel = new JLabel("Non vi sono prodotti che corrispondono in DB");
										//researchErrorLabel.setBounds(10, 9, 65, 14);
										//researchProductPanel.add(researchErrorLabel);
										System.out.println("Non vi sono prodotti che corrispondono in DB");														
									}
								}
							});
							
							mainPanel.add(researchProductPanel, "researchProductPanel");
							cl.show(mainPanel, "researchProductPanel");
						}
					});*/
					//evento aggiungi prodotto
					addProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//inserisci nome e IAP del prodotto
							
							addProductPanel = new JPanel();
							addProductPanel.setLayout(new BoxLayout(addProductPanel, BoxLayout.Y_AXIS));

							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}});
							backBtn.setBounds(85, 66, 72, 20);
							addProductPanel.add(backBtn);

							addProductLabel = new JLabel("Nome Prodotto");
							addProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(addProductLabel);

							addProductField = new JTextField();
							//addProductField.setBounds(300, 120, 144, 20);
							addProductPanel.add(addProductField);
							addProductField.setColumns(10);

							JLabel serialNumberProductLabel = new JLabel("Numero Seriale");
							serialNumberProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(serialNumberProductLabel);

							serialNumberProductField = new JTextField();
							//serialNumberProductField.setBounds(300, 150, 144, 20);
							addProductPanel.add(serialNumberProductField);
							serialNumberProductField.setColumns(10);				
							
							JLabel iapProductLabel = new JLabel("IAP");
							iapProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(iapProductLabel);

							iapProductField = new JTextField();  
							addProductPanel.add(iapProductField);
							iapProductField.setColumns(10);

							JLabel typeProductLabel = new JLabel("Tipo");
							typeProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(typeProductLabel);

							typeProductField = new JTextField();
							addProductPanel.add(typeProductField);
							typeProductField.setColumns(10);

							JLabel brandProductLabel = new JLabel("Marca");
							brandProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(brandProductLabel);

							brandProductField = new JTextField();
							addProductPanel.add(brandProductField);
							brandProductField.setColumns(10);

							addProductButton = new JButton("Aggiungi");
							addProductPanel.add(addProductButton);
							addProductButton.setBounds(300, 180, 144, 20);
							addProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if(!addProductField.getText().equalsIgnoreCase("") && !serialNumberProductField.getText().equalsIgnoreCase("") 
											&& !iapProductField.getText().equalsIgnoreCase("")&& !typeProductField.getText().equalsIgnoreCase("")
											&& !brandProductField.getText().equalsIgnoreCase("")){
										String product = citd.aggiungiProdotto(addProductField.getText(),serialNumberProductField.getText(), iapProductField.getText(), typeProductField.getText(),brandProductField.getText());

										//String product = citd.aggiungiProdotto(addProductField.getText(),serialNumberProductField.getText());
									if (product != null) {
										System.out.println(product);
									}else {
										System.out.println("Non è stato possibile inserire il prodotto in DB");														
									
									}
								}
								}
							});
														
							mainPanel.add(addProductPanel, "addProductPanel");
							cl.show(mainPanel, "addProductPanel");
						}
					});
					
					//evento elimina e modifica prodotto
					viewProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							viewProductPanel = new JPanel();
							viewProductPanel.setLayout(new BoxLayout(viewProductPanel, BoxLayout.Y_AXIS));
							tableProductPanel = new JPanel(new FlowLayout());
							researchProductPanel = new JPanel();
							
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}});
							backBtn.setBounds(85, 66, 72, 20);
							researchProductPanel.add(backBtn);

							productLabel = new JLabel("Nome Prodotto");
							researchProductPanel.add(productLabel);
							
							researchProductField = new JTextField();
							researchProductPanel.add(researchProductField);
							researchProductField.setColumns(10);

							tableProductPanel.setLayout(new BoxLayout(tableProductPanel,BoxLayout.X_AXIS));

							try {
								String data[][] = null;
								try {
									data = utility.getProductTableData();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String colonna[]={"NOME","NUMERO SERIALE"};
								jtProduct = new JTable(data,colonna);
								tableProductPanel.add(new JScrollPane(jtProduct));
								
								jtProduct.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(final MouseEvent e){
										if (e.getClickCount() == 1){
											final JTable jTable= (JTable)e.getSource();
											final int row = jTable.getSelectedRow();
											final int column = jTable.getSelectedColumn();
											valueInCell = (String)jTable.getValueAt(row, column);
											
											//EVENTO MODIFICA
											if(!valueInCell.equalsIgnoreCase("") && userLogged != null && userLogged.superuser == true ) {
												editProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														final Prodotto prod = citd.modificaProdotto(valueInCell);
														editProductPanel = new JPanel();

														backBtn = new JButton("Back");
													
														backBtn.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																cl.show(mainPanel, "viewProductPanel");
															}});
														backBtn.setBounds(85, 66, 72, 20);
														editProductPanel.add(backBtn);

														editProductPanel.setLayout(new BoxLayout(editProductPanel, BoxLayout.Y_AXIS));
														JLabel nameProductLabel = new JLabel("Nome Prodotto");
														nameProductLabel.setPreferredSize(new Dimension (50,50));
														editProductPanel.add(nameProductLabel);
														
														nameProductField = new JTextField();  
														nameProductField.setBounds(85, 6, 144, 20);
														editProductPanel.add(nameProductField);
														nameProductField.setColumns(10);
														nameProductField.setText(prod.getNome());
														
														JLabel numberProductLabel = new JLabel("Numero Seriale");
														numberProductLabel.setPreferredSize(new Dimension (50,50));
														editProductPanel.add(numberProductLabel);
														
														numberProductField = new JTextField();
														numberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(numberProductField);
														numberProductField.setColumns(10);				
														numberProductField.setText(prod.getSerialNumber());

														JLabel iapProductLabel = new JLabel("IAP");
														editProductPanel.add(iapProductLabel);
														iapProductLabel.setPreferredSize(new Dimension (50,50));

														
														editIapProductField = new JTextField();
														editProductPanel.add(editIapProductField);
														editIapProductField.setColumns(10);
														editIapProductField.setText(prod.getIAP());

														JLabel typeProductLabel = new JLabel("Tipo");
														editProductPanel.add(typeProductLabel);
														typeProductLabel.setPreferredSize(new Dimension (50,50));

														
														editTypeProductField = new JTextField();
														editProductPanel.add(editTypeProductField);
														editTypeProductField.setColumns(10);
														editTypeProductField.setText(prod.getTipo());

														JLabel brandProductLabel = new JLabel("Marca");
														editProductPanel.add(brandProductLabel);
														brandProductLabel.setPreferredSize(new Dimension (50,50));

														
														editBrandProductField = new JTextField();
														editProductPanel.add(editBrandProductField);
														editBrandProductField.setColumns(10);
														editBrandProductField.setText(prod.getMarca());

														JButton editProductButton = new JButton("Salva");
														editProductPanel.add(editProductButton);
														editProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setProdotto(nameProductField.getText(), numberProductField.getText(), editIapProductField.getText(), editTypeProductField.getText(), editBrandProductField.getText());
															}
														});
														
														mainPanel.add(editProductPanel, "editProductPanel");
														cl.show(mainPanel, "editProductPanel");
													}
												});
											}
											
											if(!valueInCell.equalsIgnoreCase("") && userLogged != null && userLogged.superuser == true) {
												deleteProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														String result = citd.eliminaProdotto(valueInCell);
														if (result != null) {
															System.out.println("Elemento eliminato dal DB");
															try {
																String data[][] = null;
																try {
																	data = utility.getProductTableData();
																} catch (IOException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																String colonna[]={"NOME","NUMERO SERIALE"};
																Model table = new Model(data,colonna);
																jtProduct.setModel(table);
																((Model) jtProduct.getModel()).fireTableDataChanged();

															}catch(Exception e) {
																System.out.println(e.getMessage());
															}
														}	
														
													}
												});
											}
										}
									}
								});

								researchProductButton = new JButton("Cerca");
								researchProductPanel.add(researchProductButton);
								researchProductButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										// if (pressButton == 0 || !found) {
										// 	pressButton++;
										// }else {
										// 	text.setText("");
										// 	researchProductPanel.remove(text);										
										// }

										Prodotto product = citd.visualizzaProdotto(researchProductField.getText());
										
										if (product != null) {
											found = true;
											System.out.println("Il prodotto che ho trovato e':" + product);
											String colonna[]={"NOME","NUMERO SERIALE"};
											String data[][] = new String[1][2];
											data[0][0] = product.getNome();
											data[0][1] = product.getSerialNumber();

											Model table = new Model(data,colonna);
											jtProduct.setModel(table);
											((Model) jtProduct.getModel()).fireTableDataChanged();
										}else {
											found=false;
											System.out.println("Non vi sono prodotti che corrispondono in DB");														
										}
									}
								});
								resetProductTableButton = new JButton("reset");
								researchProductPanel.add(resetProductTableButton);
								resetProductTableButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
											String colonna[]={"NOME","NUMERO SERIALE"};
											String data[][] = null;
											try {
												data = utility.getProductTableData();
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											researchProductField.setText("");
											Model table = new Model(data,colonna);
											jtProduct.setModel(table);
											((Model) jtProduct.getModel()).fireTableDataChanged();
									}
								});
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}		
							
							if(userLogged != null && userLogged.superuser == true ) {
								editProductBtn = new JButton("Modifica");
								editProductBtn.setBounds(85, 66, 72, 20);
								tableProductPanel.add(editProductBtn);
								
								
								deleteProductBtn = new JButton("Elimina");
								deleteProductBtn.setBounds(85, 66, 72, 20);
								tableProductPanel.add(deleteProductBtn);
							}

							viewProductPanel.add(researchProductPanel);
							viewProductPanel.add(tableProductPanel);
							
							mainPanel.add(viewProductPanel, "viewProductPanel");
							cl.show(mainPanel, "viewProductPanel");

						}
					});
					
					
					
				}
			});
			setupMenuLogout = new JMenu("Profilo");
			homeBar.add(setupMenuLogout);

			itemLogoutSetup = new JMenuItem("Logout");
			itemLogoutSetup.setLayout(new BorderLayout());
			setupMenuLogout.add(itemLogoutSetup);
			itemLogoutSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					userLogged = null;
					cl.show(mainPanel, "loginPanel");
				}
			});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
