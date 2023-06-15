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
	private JPanel mainPanel, homePanel, loginPanel, userSetupPanel, userAddPanel, productSetupPanel, researchProductPanel, addProductPanel, viewProductPanel, editProductPanel;
	private JLabel matricolaLabel, passwordLabel,addLabel, productLabel, addProductLabel, iapProductLabel;
	private JTextField matricolaField, passwordField, addProductField, serialNumberProductField, researchProductField;
	private JButton loginBtn, searchProductButton, addProductButton, researchProductButton, deleteProductBtn,  addBtn, editBtn, deleteBtn, editProductBtn, viewProductButton, saveBtn;
	private JMenuBar homeBar;
	private JMenu setupMenu;
	private JMenuItem itemUserSetup;
	String valueInCell = "";
	
	public CITD citd;
	
	public Utils utility;
	private JMenuItem itemProductSetup;

	
	public App() {	
		initialize();
	}

	
	private void initialize() {
		
		utility = new Utils();
		cl = new CardLayout(100,100);
		
		mainPanel = new JPanel(cl);
				
		frame = new JFrame("CITD");
		frame.setBounds(100,100,640,480);
		loginPanel = new JPanel();
		loginPanel.setLayout(null);

		matricolaLabel = new JLabel("matricola");
		matricolaLabel.setBounds(10, 9, 65, 14);
		loginPanel.add(matricolaLabel);
		
		matricolaField = new JTextField();
		matricolaField.setBounds(85, 6, 144, 20);
		loginPanel.add(matricolaField);
		matricolaField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setBounds(10, 39, 65, 14);
		loginPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(85, 36, 144, 20);
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
				home(userLogged);
				
			}
		});
		loginBtn.setBounds(85, 66, 72, 20);
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
					userSetupPanel.setPreferredSize(new Dimension(200, 200));
					userSetupPanel.setLayout(new BoxLayout(userSetupPanel, BoxLayout.X_AXIS));
					
				    userAddPanel = new JPanel();
				    userAddPanel.setLayout(null);
				    
					addLabel = new JLabel("Matricola");
					addLabel.setBounds(10, 9, 65, 14);
					userAddPanel.add(addLabel);
					
					matricolaField = new JTextField();
					matricolaField.setBounds(85, 6, 144, 20);
					userAddPanel.add(matricolaField);
					matricolaField.setColumns(10);
					
					addLabel = new JLabel("Password");
					addLabel.setBounds(10, 29, 65, 14);
					userAddPanel.add(addLabel);
					
					passwordField = new JTextField();
					passwordField.setBounds(85, 26, 144, 20);
					userAddPanel.add(passwordField);
					passwordField.setColumns(10);
					
					addBtn = new JButton("Aggiungi");
					addBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//System.out.println(matricolaField.getText() +" "+ passwordField.getText());
							
							Utente newUser = new Utente(matricolaField.getText(), utility.getEncryptPassword(passwordField.getText()));
							citd.inserisciNuovoUtente(newUser);
							
							
						}
					});
					addBtn.setBounds(85, 66, 150, 20);
					userAddPanel.setPreferredSize(new Dimension(300,300));
					userAddPanel.add(addBtn);
					userSetupPanel.add(userAddPanel);
					
					mainPanel.add(userSetupPanel, "userSetupPanel");
					cl.show(mainPanel, "userSetupPanel");
					
					
					try {
						
						
						BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
						int lines = 0;
						while (br.readLine() != null) lines++;
											
						String data[][] = new String[lines][2];
						br.close();
						
						br = new BufferedReader(new FileReader("./database/db_users.txt"));
						String s = "";
						int i = 0;
						while((s = br.readLine()) != null){
							data[i] = s.split(",");
							i++;
						}
						
						br.close();
						
						String column[]={"USERNAME"};
						jt = new JTable(data,column); 
					    userSetupPanel.add(new JScrollPane(jt));
					    
					    jt.addMouseListener(new MouseAdapter() {
					        @Override
					        public void mouseClicked(final MouseEvent e){
					            if (e.getClickCount() == 1){
					                final JTable jTable= (JTable)e.getSource();
					                final int row = jTable.getSelectedRow();
					                final int column = jTable.getSelectedColumn();
					                valueInCell = (String)jTable.getValueAt(row, column);
					                
					            }
					        }});
					    
					    editBtn = new JButton("Edit");
					    editBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if(valueInCell != "") {
																		
									userSetupPanel = new JPanel();
									userSetupPanel.setPreferredSize(new Dimension(200, 200));
									
									
									addLabel = new JLabel("Matricola");
									addLabel.setBounds(10, 9, 65, 14);
									userSetupPanel.add(addLabel);
									matricolaField = new JTextField();
									matricolaField.setBounds(85, 6, 144, 20);
									userSetupPanel.add(matricolaField);
									matricolaField.setColumns(10);
									matricolaField.setText(valueInCell);
									
									//da sostituire con l'utente ottenuto in fase di return details
									final Utente editUtente = citd.getDetailsUtente(valueInCell);
									
									saveBtn = new JButton("Save");
									saveBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												citd.updateUtente(editUtente);
											}});
									saveBtn.setBounds(85, 66, 72, 20);
								    userSetupPanel.add(saveBtn);
								    
								    mainPanel.add(userSetupPanel, "userSetupPanelEdit");
									cl.show(mainPanel, "userSetupPanelEdit");
								}
							}
						});
					    editBtn.setBounds(85, 66, 72, 20);
					    userSetupPanel.add(editBtn);
					    
					    deleteBtn = new JButton("Delete");
					    deleteBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if(valueInCell != "") {
																	
									System.out.println(valueInCell);
									//frame.getContentPane().remove(userSetupPanel);
									citd.eliminaUtente(valueInCell.toString());									
								}
							}
						});
					    deleteBtn.setBounds(85, 86, 72, 20);
					    userSetupPanel.add(deleteBtn);
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
									citd = new CITD();
									Prodotto product = citd.visualizzaProdotto(researchProductField.getText());
									if (product != null) {
										System.out.println("Il prodotto che ho trovato e':" + product);
									}else {
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
							addProductPanel.setLayout(null);

							addProductLabel = new JLabel("Nome Prodotto");
							addProductLabel.setBounds(200, 120, 185, 14);
							addProductPanel.add(addProductLabel);
							
							addProductField = new JTextField();
							addProductField.setBounds(300, 120, 144, 20);
							addProductPanel.add(addProductField);
							addProductField.setColumns(10);

							iapProductLabel = new JLabel("IAP Prodotto");
							iapProductLabel.setBounds(200, 150, 185, 14);
							addProductPanel.add(iapProductLabel);
							
							serialNumberProductField = new JTextField();
							serialNumberProductField.setBounds(300, 150, 144, 20);
							addProductPanel.add(serialNumberProductField);
							serialNumberProductField.setColumns(10);				
							
							addProductButton = new JButton("Aggiungi");
							addProductPanel.add(addProductButton);
							addProductButton.setBounds(250, 190, 150, 20);
							addProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									String product = citd.aggiungiProdotto(addProductField.getText(),serialNumberProductField.getText());
									if (product != null) {
										System.out.println(product);
									}else {
										System.out.println("Non è stato possibile inserire il prodotto in DB");														
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
														
														editProductPanel = new JPanel();
										
									//String nome, String serial_number, int IAP, int tipo, int marca, int barcode, boolean inStock
														//String nome, String serial_number, int IAP, int marca
														
														
														JLabel nameProductLabel = new JLabel("Nome Prodotto");
														nameProductLabel.setBounds(10, 9, 65, 14);
														editProductPanel.add(nameProductLabel);
														
														JTextField nameProductField = new JTextField();
														nameProductField.setBounds(85, 6, 144, 20);
														editProductPanel.add(nameProductField);
														nameProductField.setColumns(10);
														//nameProductField.setText(valueInCell);

														JButton editNameProductButton = new JButton("Edit");
														editProductPanel.add(editNameProductButton);
														//addProductButton.setBounds(250, 190, 150, 20);
														
														JLabel numberProductLabel = new JLabel("Numero Seriale");
														numberProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(numberProductLabel);
														
														JTextField numberProductField = new JTextField();
														numberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(numberProductField);
														numberProductField.setColumns(10);				
														
														mainPanel.add(editProductPanel, "editProductPanel");
														cl.show(mainPanel, "editProductPanel");
														
														/*JLabel iapProductLabel = new JLabel("IAP");
														//addProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(iapProductLabel);
														
														JTextField iapProductField = new JTextField();
														//serialNumberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(iapProductField);
														iapProductField.setColumns(10);		
														
														
														
														JLabel brandProductLabel = new JLabel("Marca");
														//addProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(brandProductLabel);
														
														JTextField brandProductField = new JTextField();
														//serialNumberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(brandProductField);
														brandProductField.setColumns(10);	*/	
														
														
														
														/*JLabel nameProductLabel = new JLabel("Nome Prodotto");
														//addProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(nameProductLabel);
														
														
														JLabel nameProductLabel = new JLabel("Nome Prodotto");
														//addProductLabel.setBounds(200, 120, 185, 14);
														editProductPanel.add(nameProductLabel);*/
														

													
														
														
														
														
														
														
														
													
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
