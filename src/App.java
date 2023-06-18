import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


public class App {
	
	static CardLayout cl;
	
	private JFrame frame;
	private JTable jt, jtProduct;
	private JPanel mainPanel, homePanel, loginPanel, userSetupPanel, userAddPanel, productSetupPanel, researchProductPanel, addProductPanel, viewProductPanel, editProductPanel, userPanel;
	private JLabel matricolaLabel, passwordLabel,addLabel, productLabel, addProductLabel,researchErrorLabel, iapProductLabel;
	private JTextField matricolaField, passwordField, addProductField, serialNumberProductField, researchProductField,iapProductField,typeProductField,brandProductField, nameProductField, numberProductField, editIapProductField,editTypeProductField,editBrandProductField,nomeField,cognomeField,emailField;
	private JRadioButton superuserField;
	private JButton loginBtn, searchProductButton, addProductButton, researchProductButton, deleteProductBtn,  addBtn, editBtn, deleteBtn, editProductBtn, viewProductButton, saveBtn;
	private JMenuBar homeBar;
	private JMenu setupMenu;
	private JMenuItem itemUserSetup;
	private JTextArea text;

	String valueInCell = "";
	String[] arrayString;
	int pressButton=0;
	Boolean found= false;
	
	public CITD citd;
	
	public Utils utility;
	private JMenuItem itemProductSetup;

	
	public App() {	
		initialize();
	}

	
	private void initialize() {
		
		utility = new Utils();
		cl = new CardLayout();
		
		mainPanel = new JPanel(cl);
				
		frame = new JFrame("CITD");
		frame.setBounds(600,300,640,480);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		loginPanel = new JPanel();
		loginPanel.setLayout(null);

		matricolaLabel = new JLabel("matricola");
		matricolaLabel.setBounds(180, 150, 70, 15);
		loginPanel.add(matricolaLabel);
		
		matricolaField = new JTextField();
		matricolaField.setBounds(250, 150, 144, 20);
		loginPanel.add(matricolaField);
		matricolaField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setBounds(180, 180, 70, 15);
		loginPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(250, 180, 144, 20);
		loginPanel.add(passwordField);
		passwordField.setColumns(10);
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(matricolaField.getText() +" "+ passwordField.getText());
				Utente userLogged = null;
				citd = new CITD();
				userLogged = citd.Login(matricolaField.getText(), passwordField.getText());
				//frame.getContentPane().remove(loginPanel);
				if(userLogged != null)
					home(userLogged);
				
			}
		});
		loginBtn.setBounds(250, 210, 144, 20);
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
					
				    userAddPanel = new JPanel();
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
					addBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println(passwordField.getText());
							Utente newUser = new Utente(matricolaField.getText(), passwordField.getText(), nomeField.getText(), cognomeField.getText(), emailField.getText(), superuserField.isSelected());
							System.out.println(newUser.nome);
							citd.inserisciNuovoUtente(newUser);
							
							
						}
					});
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
					    
					    editBtn = new JButton("Edit");
					    editBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println(arrayString[0]);
								if(!arrayString.equals(null)) {
																		
									userSetupPanel = new JPanel();
									userSetupPanel.setPreferredSize(new Dimension(200, 200));
																		
									addLabel = new JLabel("Matricola");
									addLabel.setBounds(10, 9, 65, 14);
									userSetupPanel.add(addLabel);

									matricolaField = new JTextField();
									matricolaField.setBounds(85, 6, 144, 20);
									userSetupPanel.add(matricolaField);
									matricolaField.setColumns(10);
									matricolaField.setText(arrayString[0]);

									addLabel = new JLabel("Password");
									addLabel.setBounds(10, 29, 65, 14);
									userSetupPanel.add(addLabel);
									
									passwordField = new JTextField();
									passwordField.setBounds(85, 26, 144, 20);
									userSetupPanel.add(passwordField);
									passwordField.setColumns(10);

									addLabel = new JLabel("Nome");
									addLabel.setBounds(10, 49, 65, 14);
									userSetupPanel.add(addLabel);
									
									nomeField = new JTextField();
									nomeField.setBounds(85, 46, 144, 20);
									userSetupPanel.add(nomeField);
									nomeField.setColumns(10);
									nomeField.setText(arrayString[2]);

									addLabel = new JLabel("Cognome");
									addLabel.setBounds(10, 69, 65, 14);
									userSetupPanel.add(addLabel);
									
									cognomeField = new JTextField();
									cognomeField.setBounds(85, 66, 144, 20);
									userSetupPanel.add(cognomeField);
									cognomeField.setColumns(10);
									cognomeField.setText(arrayString[3]);

									addLabel = new JLabel("Email");
									addLabel.setBounds(10, 89, 65, 14);
									userSetupPanel.add(addLabel);
									
									
									emailField = new JTextField();
									emailField.setBounds(85, 86, 144, 20);
									userSetupPanel.add(emailField);
									emailField.setColumns(10);
									emailField.setText(arrayString[4]);

									addLabel = new JLabel("Amministratore");
									addLabel.setBounds(10, 109, 150, 14);
									userSetupPanel.add(addLabel);
									
									superuserField = new JRadioButton();
									superuserField.setBounds(150, 106, 144, 20);
									userSetupPanel.add(superuserField);
									superuserField.setSelected(Integer.valueOf(arrayString[5]) == 1 ? true : false);
									
									//da sostituire con l'utente ottenuto in fase di return details
									
									saveBtn = new JButton("Save");
									saveBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												citd.updateUtente(arrayString[0], new Utente(matricolaField.getText(),passwordField.getText(),nomeField.getText(),cognomeField.getText(),emailField.getText(),superuserField.isSelected()));
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
			setupMenu.add(itemUserSetup);

			itemProductSetup = new JMenuItem("Gestione Prodotti");
			itemProductSetup.setLayout(new BorderLayout());
			setupMenu.add(itemProductSetup);
			itemProductSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					productSetupPanel = new JPanel();
					//All'interno del panel inserisco un tris di bottoni
					//RICERCA PRODOTTO, AGGIUNTA ED ELIMINAZIONE
					searchProductButton = new JButton("Cerca Prodotto");
					productSetupPanel.add(searchProductButton);
					
					addProductButton = new JButton("Aggiungi Prodotto");
					productSetupPanel.add(addProductButton);
					
					viewProductButton = new JButton("Visualizza Prodotti");
					productSetupPanel.add(viewProductButton);
					
					mainPanel.add(productSetupPanel, "productSetupPanel");
					cl.show(mainPanel, "productSetupPanel");
					
					//evento ricerca prodotto
					searchProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							researchProductPanel = new JPanel();
														
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

									citd = new CITD();
									Prodotto product = citd.visualizzaProdotto(researchProductField.getText());
									if (product != null) {
										found = true;
										System.out.println("Il prodotto che ho trovato e':" + product);
										text = new JTextArea();
										researchProductPanel.add(text);
										String textarea = "Prodotto trovato:\n Nome: " + product.getNome() + "\n Numero seriale: " + product.getSerialNumber() +
														"\n IAP: " + product.getIAP() + "\n Tipo: " + product.getTipo() + "\n Marca: " + product.getMarca();
										text.setText(textarea);
									}else {
										found=false;
										/*researchErrorLabel = new JLabel("Non vi sono prodotti che corrispondono in DB");
										researchErrorLabel.setBounds(10, 9, 65, 14);
										researchProductPanel.add(researchErrorLabel);*/
										System.out.println("Non vi sono prodotti che corrispondono in DB");														
									}
								}
							});
							
							mainPanel.add(researchProductPanel, "researchProductPanel");
							cl.show(mainPanel, "researchProductPanel");
						}
					});
					//evento aggiungi prodotto
					addProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//inserisci nome e IAP del prodotto
							
							addProductPanel = new JPanel();
							addProductPanel.setLayout(new BoxLayout(addProductPanel, BoxLayout.Y_AXIS));
							addProductLabel = new JLabel("Nome Prodotto");
							//addProductLabel.setBounds(200, 120, 185, 14);
							addProductPanel.add(addProductLabel);
							addProductField = new JTextField();
							addProductField.setBounds(300, 120, 144, 20);
							addProductPanel.add(addProductField);
							addProductField.setColumns(10);

							JLabel serialNumberProductLabel = new JLabel("Numero Seriale");
							//serialNumberProductLabel.setBounds(200, 150, 185, 14);
							addProductPanel.add(serialNumberProductLabel);
							serialNumberProductField = new JTextField();
							//serialNumberProductField.setBounds(300, 150, 144, 20);
							addProductPanel.add(serialNumberProductField);
							serialNumberProductField.setColumns(10);				
							
							JLabel iapProductLabel = new JLabel("IAP");
							addProductPanel.add(iapProductLabel);
							iapProductField = new JTextField();  
							addProductPanel.add(iapProductField);
							iapProductField.setColumns(10);

							JLabel typeProductLabel = new JLabel("Tipo");
							addProductPanel.add(typeProductLabel);
							typeProductField = new JTextField();
							addProductPanel.add(typeProductField);
							typeProductField.setColumns(10);

							JLabel brandProductLabel = new JLabel("Marca");
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
							
							addProductPanel.setPreferredSize(new Dimension(300,300));
							
							mainPanel.add(addProductPanel, "addProductPanel");
							cl.show(mainPanel, "addProductPanel");
						}
					});
					
					//evento elimina e modifica prodotto
					viewProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							viewProductPanel = new JPanel();
							viewProductPanel.setLayout(new BoxLayout(viewProductPanel,BoxLayout.X_AXIS));
							productSetupPanel.setVisible(false);

							try {
								BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
								int lines = 0;
								while (br.readLine() != null) lines++;
								String data[][] = new String[lines][2];
								br.close();
								br = new BufferedReader(new FileReader("./database/db_product.txt"));
								String s = "";
								int i = 0;
								while((s = br.readLine()) != null){
									data[i] = s.split(",");
									i++;
								}
								br.close();
								String colonna[]={"NOME","NUMERO SERIALE"};
								jtProduct = new JTable(data,colonna);
								viewProductPanel.add(new JScrollPane(jtProduct));
								
								jtProduct.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(final MouseEvent e){
										if (e.getClickCount() == 1){
											final JTable jTable= (JTable)e.getSource();
											final int row = jTable.getSelectedRow();
											final int column = jTable.getSelectedColumn();
											valueInCell = (String)jTable.getValueAt(row, column);
											
											//EVENTO MODIFICA
											if(!valueInCell.equalsIgnoreCase("")) {
												editProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														final Prodotto prod = citd.modificaProdotto(valueInCell);
														editProductPanel = new JPanel();
														JLabel nameProductLabel = new JLabel("Nome Prodotto");
														nameProductLabel.setBounds(10, 9, 65, 14);
														editProductPanel.add(nameProductLabel);
														
														nameProductField = new JTextField();  
														nameProductField.setBounds(85, 6, 144, 20);
														editProductPanel.add(nameProductField);
														nameProductField.setColumns(10);
														nameProductField.setText(prod.getNome());

														//edit nome prodotto
														JButton editNameProductButton = new JButton("Edit");
														editProductPanel.add(editNameProductButton);
														editNameProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setNome(nameProductField.getText());
															}
														});
														
														
														JLabel numberProductLabel = new JLabel("Numero Seriale");
														numberProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(numberProductLabel);
														
														numberProductField = new JTextField();
														numberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(numberProductField);
														numberProductField.setColumns(10);				
														numberProductField.setText(prod.getSerialNumber());

														//edit numero seriale prodotto
														JButton editNumberProductButton = new JButton("Edit");
														editProductPanel.add(editNumberProductButton);
														editNumberProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setSerialNumber(numberProductField.getText());
															}
														});
														
														JLabel iapProductLabel = new JLabel("IAP");
														editProductPanel.add(iapProductLabel);
														
														editIapProductField = new JTextField();
														editProductPanel.add(editIapProductField);
														editIapProductField.setColumns(10);
														editIapProductField.setText(prod.getIAP());
														
														//edit iap prodotto
														JButton editIapProductButton = new JButton("Edit");
														editProductPanel.add(editIapProductButton);
														editIapProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setIAP(editIapProductField.getText());
															}
														});
														
														JLabel typeProductLabel = new JLabel("Tipo");
														editProductPanel.add(typeProductLabel);
														
														editTypeProductField = new JTextField();
														editProductPanel.add(editTypeProductField);
														editTypeProductField.setColumns(10);
														editTypeProductField.setText(prod.getTipo());

														//edit tipo prodotto
														JButton editTypeProductButton = new JButton("Edit");
														editProductPanel.add(editTypeProductButton);
														editTypeProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setTipo(editTypeProductField.getText());
															}
														});
														
														JLabel brandProductLabel = new JLabel("Marca");
														editProductPanel.add(brandProductLabel);
														
														editBrandProductField = new JTextField();
														editProductPanel.add(editBrandProductField);
														editBrandProductField.setColumns(10);
														editBrandProductField.setText(prod.getMarca());
														
														//edit marca prodotto
														JButton editBrandProductButton = new JButton("Edit");
														editProductPanel.add(editBrandProductButton);
														editBrandProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																prod.setMarca(editBrandProductField.getText());
															}
														});
														
														
														mainPanel.add(editProductPanel, "editProductPanel");
														cl.show(mainPanel, "editProductPanel");
													}
												});
											}
											
											if(!valueInCell.equalsIgnoreCase("")) {
												deleteProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														String result = citd.eliminaProdotto(valueInCell);
														if (result != null) {
															System.out.println("Elemento eliminato dal DB");
															try {
																BufferedReader br = new BufferedReader(new FileReader("./database/db_product.txt"));
																int lines = 0;
																while (br.readLine() != null) lines++;
																String data[][] = new String[lines][2];
																br.close();
																br = new BufferedReader(new FileReader("./database/db_product.txt"));
																String s = "";
																int i = 0;
																while((s = br.readLine()) != null){
																	data[i] = s.split(",");
																	i++;
																}
																br.close();
																String colonna[]={"NOME","NUMERO SERIALE"};
																Model table = new Model(data,colonna);
																jtProduct.setModel(table);
																((Model) jTable.getModel()).fireTableDataChanged();

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
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}		
							
							editProductBtn = new JButton("Modifica");
							editProductBtn.setBounds(85, 66, 72, 20);
							viewProductPanel.add(editProductBtn);
							
							
							deleteProductBtn = new JButton("Elimina");
							deleteProductBtn.setBounds(85, 66, 72, 20);
							viewProductPanel.add(deleteProductBtn);
							
							mainPanel.add(viewProductPanel, "viewProductPanel");
							cl.show(mainPanel, "viewProductPanel");

						}
					});
					
					
					
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
