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
	
	private JFrame frame;
	private JTable jt;
	private JPanel loginPanel, userSetupPanel, userAddPanel, productSetupPanel, researchProductPanel, addProductPanel, deleteProductPanel;
	private JLabel matricolaLabel, passwordLabel,addLabel, productLabel, addProductLabel, iapProductLabel, deleteProductLabel;
	private JTextField matricolaField, passwordField, addProductField, serialNumberProductField, researchProductField, deleteProductField;
	private JButton loginBtn, searchProductButton, addProductButton, deleteProductButton, researchProductButton, deleteProductBut,  addBtn, editBtn;
	private JMenuBar homeBar;
	private JMenu setupMenu;
	private JMenuItem itemUserSetup;
	String valueInCell = "";
	
	public CITD citd;
	
	public Utils utility;
	private JMenuItem itemProductSetup;

	
	public App() {
		utility = new Utils();
		initialize();
	}

	
	private void initialize() {
		
		frame = new JFrame("CITD");
		frame.setBounds(100,100,640,480);
		loginPanel = new JPanel();
		frame.add(loginPanel);
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
		
	}
	
	private void home(Utente utente){
		try {
			System.out.println(utente.matricola);
			// DA RIMUOVERE
			/*frame = new JFrame("CITD - " + utente.matricola);
			frame.setBounds(100, 100, 663, 420);
			frame.getContentPane().setLayout(new CardLayout(0, 0));*/
			loginPanel.setVisible(false);
			// -----------
			homeBar = new JMenuBar();
			frame.setJMenuBar(homeBar);
			setupMenu = new JMenu("Gestione");
			homeBar.add(setupMenu);
			itemUserSetup = new JMenuItem("Gestione Utenti");
			itemUserSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().revalidate();
					// da rivedere funzionamento apertura panel
					userSetupPanel = new JPanel();
					userSetupPanel.setPreferredSize(new Dimension(200, 200));
					frame.add(userSetupPanel);
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
					userSetupPanel.setVisible(true);
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
																	
									System.out.println(valueInCell);
									//frame.getContentPane().remove(userSetupPanel);
									userSetupPanel.setVisible(false);	
		
									userSetupPanel = new JPanel();
									userSetupPanel.setPreferredSize(new Dimension(200, 200));
									frame.add(userSetupPanel);
									
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
									
									editBtn = new JButton("Edit");
									 editBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												citd.updateUtente(editUtente);
											}});
									editBtn.setBounds(85, 66, 72, 20);
								    userSetupPanel.add(editBtn);
								}
							}
						});
					    editBtn.setBounds(85, 66, 72, 20);
					    userSetupPanel.add(editBtn);
					    
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
					frame.getContentPane().revalidate();
					productSetupPanel = new JPanel();
					frame.add(productSetupPanel);	
					//All'interno del panel inserisco un tris di bottoni
					//RICERCA PRODOTTO, AGGIUNTA ED ELIMINAZIONE
					searchProductButton = new JButton("Cerca Prodotto");
					productSetupPanel.add(searchProductButton);
					
					addProductButton = new JButton("Aggiungi Prodotto");
					productSetupPanel.add(addProductButton);
					
					deleteProductButton = new JButton("Elimina Prodotto");
					productSetupPanel.add(deleteProductButton);
					
					//evento ricerca prodotto
					searchProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							researchProductPanel = new JPanel();
							frame.add(researchProductPanel);
							productSetupPanel.setVisible(false);
							
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
						}
					});


					//evento aggiungi prodotto
					addProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//inserisci nome e IAP del prodotto
							addProductPanel = new JPanel();
							frame.add(addProductPanel);
							productSetupPanel.setVisible(false);

							addProductLabel = new JLabel("Nome Prodotto");
							//addProductLabel.setBounds(10, 9, 65, 14);
							addProductPanel.add(addProductLabel);
							
							addProductField = new JTextField();
							addProductPanel.add(addProductField);
							addProductField.setColumns(10);

							iapProductLabel = new JLabel("IAP Prodotto");
							iapProductLabel.setBounds(10, 9, 65, 14);
							addProductPanel.add(iapProductLabel);
							
							serialNumberProductField = new JTextField();
							addProductPanel.add(serialNumberProductField);
							serialNumberProductField.setColumns(10);
							
							
							addProductButton = new JButton("Aggiungi");
							addProductPanel.add(addProductButton);
							addProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									citd = new CITD();
									String product = citd.aggiungiProdotto(addProductField.getText(),serialNumberProductField.getText());
									if (product != null) {
										System.out.println(product);
									}else {
										System.out.println("Non è stato possibile inserire il prodotto in DB");														
									}
								}
							});
						}
					});
					
					//evento elimina prodotto
					deleteProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							deleteProductPanel = new JPanel();
							frame.add(deleteProductPanel);
							productSetupPanel.setVisible(false);

							deleteProductLabel = new JLabel("Inserisci il nome del prodotto da eliminare");
							//addProductLabel.setBounds(10, 9, 65, 14);
							deleteProductPanel.add(deleteProductLabel);
							
							deleteProductField = new JTextField();
							deleteProductPanel.add(deleteProductField);
							deleteProductField.setColumns(10);
							
							deleteProductBut = new JButton("Elimina");
							deleteProductPanel.add(deleteProductBut);
							deleteProductBut.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									citd = new CITD();
									String product = citd.eliminaProdotto(deleteProductField.getText());
									if (product != null) {
										System.out.println(product);
									}else {
										System.out.println("Non è stato possibile inserire il prodotto in DB");														
									}
								}
							});
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
