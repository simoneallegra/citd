import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.*;
import javax.swing.text.AbstractDocument.LeafElement;


public class App {

	static CardLayout cl;

	private JFrame frame;
	private JTable jt, jtProduct;

	private JButton loginBtn,backBtn,linkBtn,detailrequestBtn,renewalBtn, careBtn, problemsBtn ,proiezioniSpesaProductButton, addProductButton, researchProductButton, deleteProductBtn,  addBtn, editBtn, deleteBtn, editProductBtn, viewProductButton, saveBtn,resetProductTableButton, assignProductButton, detailProductButton,assignBtn,filtro,careProductButton,renewalLicenseButton,acceptMaintenanceBtn,denyMaintenanceBtn,careRequestBtn,newProductRequestBtn,madeRequestBtn,managementRequestBtn, rentProductButton, openFileBtn, updateRentBtn,updateDocBtn,acceptRequestBtn, denyRequestBtn;
	private JPanel mainPanel, homePanel, loginPanel, userSetupPanel, userAddPanel, productSetupPanel, researchProductPanel, addProductPanel, viewProductPanel,tableProductPanel, editProductPanel, userPanel, detailProductPanel,proiezioniSpesaPanel,resultPanel,careProductPanel, rentProductPanel,renewalLicensePanel,careRequestPanel,newProductRequestPanel,madeRequestPanel,managementRequestPanel,completeRequestPanel;
	private JLabel matricolaLabel, passwordLabel,addLabel, productLabel, addProductLabel;
	private JTextField matricolaField, passwordField, addProductField, serialNumberProductField, researchProductField,iapProductField,typeProductField,brandProductField, nameProductField, numberProductField, editIapProductField,editTypeProductField,editBrandProductField,nomeField,cognomeField,emailField,reasonProductField,typeProdField,costProductField,dateProductField,linkProductField;
	private JTextField ricercaPossesso, ricercaTipo, ricercaCostiMin,ricercaCostiMax, ricercaImpiegatoAssegnato, ricercaData, ricercaCadenza, newScadenzaField,scadenza;
	private JRadioButton superuserField;
	private JMenuBar homeBar;
	private JMenu setupMenu,setupMenuLogout;
	private JMenuItem itemUserSetup,itemProductSetup,itemLogoutSetup;

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
				System.out.println(matricolaField.getText() +" "+ passwordField.getText());

				citd = new CITD("./database/db_users.txt","./database/db_products.txt","./database/db_requests.txt","./database/db_request_newproduct.txt");

				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						citd.saveAllInDB();
					}
				});

				userLogged = citd.Login(matricolaField.getText(), passwordField.getText());
				//frame.getContentPane().remove(loginPanel);
				if(userLogged != null) {
					System.out.println(userLogged.getIsSuperuser());
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
						String data [][] = utility.listToMatrixString(citd.getListaUtenti());
						String column[] = {"MATRICOLA","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
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
								Utente newUser = new Utente(matricolaField.getText(), utility.getEncryptPassword(passwordField.getText()), nomeField.getText(), cognomeField.getText(), emailField.getText(), superuserField.isSelected());
								citd.inserisciNuovoUtente(newUser);
								try {
									userPanel = new JPanel();
									userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
									String data [][]=utility.listToMatrixString(citd.getListaUtenti());
									String column[]={"MATRICOLA","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
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
									nomeField.setText(arrayString[1]);

									addLabel = new JLabel("Cognome");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);

									cognomeField = new JTextField();
									cognomeField.setBounds(85, 66, 144, 20);
									userSetupPanel.add(cognomeField);
									cognomeField.setColumns(10);
									cognomeField.setText(arrayString[2]);

									addLabel = new JLabel("Email");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);


									emailField = new JTextField();
									emailField.setBounds(85, 86, 144, 20);
									userSetupPanel.add(emailField);
									emailField.setColumns(10);
									emailField.setText(arrayString[3]);

									addLabel = new JLabel("Amministratore");
									addLabel.setPreferredSize(new Dimension(200,50));
									userSetupPanel.add(addLabel);

									superuserField = new JRadioButton();
									superuserField.setBounds(150, 106, 144, 20);
									userSetupPanel.add(superuserField);									
									superuserField.setSelected(Boolean.valueOf(arrayString[4]));

									//da sostituire con l'utente ottenuto in fase di return details

									saveBtn = new JButton("Save");
									saveBtn.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											citd.updateUtente(arrayString[0], matricolaField.getText(), passwordField.getText(), nomeField.getText(), cognomeField.getText(),emailField.getText(),superuserField.isSelected());
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
									citd.eliminaUtente(arrayString[0].toString());
									try {
										userPanel = new JPanel();
										userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
										String data [][]=utility.listToMatrixString(citd.getListaUtenti());
										String column[]={"MATRICOLA","NOME","COGNOME","EMAIL","AMMINISTRATORE"};
										Model table = new Model(data,column);
										jt.setModel(table);
										((Model) jt.getModel()).fireTableDataChanged();
									}
									catch(Exception e){
										System.out.println(e.getMessage());
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
			if(userLogged.getIsSuperuser()) setupMenu.add(itemUserSetup);

			itemProductSetup = new JMenuItem("Gestione Prodotti");
			itemProductSetup.setLayout(new BorderLayout());
			setupMenu.add(itemProductSetup);
			itemProductSetup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					productSetupPanel = new JPanel();

					proiezioniSpesaProductButton = new JButton("Proiezioni spesa");
					if(userLogged.getIsSuperuser()) productSetupPanel.add(proiezioniSpesaProductButton);

					addProductButton = new JButton("Aggiungi Prodotto");
					productSetupPanel.add(addProductButton);

					viewProductButton = new JButton("Visualizza Prodotti");
					productSetupPanel.add(viewProductButton);

					//manutenzione = care
					careProductButton = new JButton("Gestisci Manutenzioni");
					productSetupPanel.add(careProductButton);

					rentProductButton = new JButton("Gestisci Noleggio");
					productSetupPanel.add(rentProductButton);

					//rinnovo licenze
					renewalLicenseButton = new JButton("Rinnovo Licenze");
					productSetupPanel.add(renewalLicenseButton);

					//richiesta manutenzione
					careRequestBtn = new JButton("Richiesta Manutenzioni");
					productSetupPanel.add(careRequestBtn);					

					//richiesta nuovo prodotto
					newProductRequestBtn = new JButton("Richiesta Nuovo Prodotto");
					productSetupPanel.add(newProductRequestBtn);

					//visualizza richieste
					madeRequestBtn = new JButton("Richieste Effettuate");
					productSetupPanel.add(madeRequestBtn);

					//accettazione nuovo prodotto
					managementRequestBtn = new JButton("Gestisci Richieste");
					productSetupPanel.add(managementRequestBtn);

					if(userLogged != null && userLogged.getIsSuperuser() == false ) {
						addProductButton.setVisible(false);
						careProductButton.setVisible(false);
						renewalLicenseButton.setVisible(false);
						rentProductButton.setVisible(false);
						managementRequestBtn.setVisible(false);

					}else {
						careRequestBtn.setVisible(false);
						newProductRequestBtn.setVisible(false);
						madeRequestBtn.setVisible(false);
					}

					mainPanel.add(productSetupPanel, "productSetupPanel");
					cl.show(mainPanel, "productSetupPanel");

					//Proiezioni di spesa
					proiezioniSpesaProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							proiezioniSpesaPanel = new JPanel();
							proiezioniSpesaPanel.setLayout(new BoxLayout(proiezioniSpesaPanel, BoxLayout.Y_AXIS));

							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}});
							backBtn.setBounds(85, 66, 72, 20);
							proiezioniSpesaPanel.add(backBtn);

							JPanel ricercaPanel = new JPanel(new FlowLayout());
							// JPanel ricercaPanel2 = new JPanel(new FlowLayout());
							resultPanel = new JPanel();

							JLabel possessoLabel = new JLabel("Possesso");
							JLabel tipoLabel = new JLabel("Tipo");
							JLabel costiMinLabel = new JLabel("Costo minimo");
							JLabel costiMaxLabel = new JLabel("Costo massimo");
							JLabel impiegatoAssegnatoLabel = new JLabel("Impiegato assegnato");
							JLabel dataLabel = new JLabel("Data scadenza");
							JLabel cadenzaLabel = new JLabel("Cadenza");

							ricercaPossesso = new JTextField();
							ricercaPossesso.setColumns(10);
							ricercaTipo = new JTextField();
							ricercaTipo.setColumns(10);
							ricercaCostiMin = new JTextField();
							ricercaCostiMin.setColumns(10);
							ricercaCostiMax = new JTextField();
							ricercaCostiMax.setColumns(10);
							ricercaImpiegatoAssegnato = new JTextField();
							ricercaImpiegatoAssegnato.setColumns(10);
							ricercaData = new JTextField();
							ricercaData.setColumns(10);
							ricercaCadenza = new JTextField();
							ricercaCadenza.setColumns(10);	

							ricercaPanel.add(possessoLabel);
							ricercaPanel.add(ricercaPossesso);
							ricercaPanel.add(tipoLabel);
							ricercaPanel.add(ricercaTipo);
							ricercaPanel.add(costiMinLabel);
							ricercaPanel.add(ricercaCostiMin);
							ricercaPanel.add(costiMaxLabel);
							ricercaPanel.add(ricercaCostiMax);
							ricercaPanel.add(impiegatoAssegnatoLabel);
							ricercaPanel.add(ricercaImpiegatoAssegnato);
							ricercaPanel.add(dataLabel);
							ricercaPanel.add(ricercaData);
							ricercaPanel.add(cadenzaLabel);
							ricercaPanel.add(ricercaCadenza);

							String colonna[]={"COSTO "};
							final String filteredData[][] = new String[1][1];
							jt = new JTable(filteredData,colonna);
							resultPanel.add(new JScrollPane(jt));

							filtro = new JButton("Filtra");
							filtro.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									try {
										String filteredData[][] = citd.getProiezioni(ricercaPossesso.getText(),ricercaTipo.getText(),ricercaCostiMin.getText(),ricercaCostiMax.getText(),ricercaImpiegatoAssegnato.getText(),ricercaData.getText(),ricercaCadenza.getText());
										String colonna[]={"COSTO " + (ricercaCadenza.getText().isEmpty() ? "MENSILE" : ricercaCadenza.getText().toUpperCase())};
										Model table = new Model(filteredData,colonna);
										jt.setModel(table);
										((Model) jt.getModel()).fireTableDataChanged();

									} catch (IOException e) {
										e.printStackTrace();
									}
								}});
							filtro.setBounds(85, 66, 72, 20);
							ricercaPanel.add(filtro);

							proiezioniSpesaPanel.add(ricercaPanel);
							proiezioniSpesaPanel.add(resultPanel);

							mainPanel.add(proiezioniSpesaPanel, "proiezioniSpesaPanel");
							cl.show(mainPanel, "proiezioniSpesaPanel");
						}
					});

					//evento aggiungi prodotto
					addProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
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

							/*JLabel iapProductLabel = new JLabel("IAP");
							iapProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(iapProductLabel);

							iapProductField = new JTextField();  
							addProductPanel.add(iapProductField);
							iapProductField.setColumns(10);*/

							JLabel serialNumberProductLabel = new JLabel("Numero Seriale");
							serialNumberProductLabel.setPreferredSize(new Dimension(200,50));
							addProductPanel.add(serialNumberProductLabel);

							serialNumberProductField = new JTextField();
							//serialNumberProductField.setBounds(300, 150, 144, 20);
							addProductPanel.add(serialNumberProductField);
							serialNumberProductField.setColumns(10);				

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

							
							JLabel typeProdLabel = new JLabel("Tipologia Acquisto");
							typeProdLabel.setPreferredSize(new Dimension(200,40));
							addProductPanel.add(typeProdLabel);
							typeProdField = new JTextField();  
							addProductPanel.add(typeProdField);
							typeProdField.setColumns(10);
							JLabel costProductLabel = new JLabel("Costo");
							costProductLabel.setPreferredSize(new Dimension(200,40));
							addProductPanel.add(costProductLabel);
							costProductField = new JTextField();
							addProductPanel.add(costProductField);
							costProductField.setColumns(10);
							JLabel dateProductLabel = new JLabel("Scadenza");
							dateProductLabel.setPreferredSize(new Dimension(200,40));
							addProductPanel.add(dateProductLabel);
							dateProductField = new JTextField();
							addProductPanel.add(dateProductField);
							dateProductField.setColumns(10);
							JLabel linkProductLabel = new JLabel("Link");
							linkProductLabel.setPreferredSize(new Dimension(200,40));
							addProductPanel.add(linkProductLabel);
							linkProductField = new JTextField();
							addProductPanel.add(linkProductField);
							linkProductField.setColumns(10);

							
							
							addProductButton = new JButton("Aggiungi");
							addProductPanel.add(addProductButton);
							addProductButton.setBounds(300, 180, 144, 20);
							addProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if(!addProductField.getText().equalsIgnoreCase("") && !serialNumberProductField.getText().equalsIgnoreCase("") 
											&& !typeProductField.getText().equalsIgnoreCase("") && !brandProductField.getText().equalsIgnoreCase("")
											&& !typeProdField.getText().equalsIgnoreCase("")&& !costProductField.getText().equalsIgnoreCase("")){
											//&& !dateProductField.getText().equalsIgnoreCase("")	&& !linkProductField.getText().equalsIgnoreCase("")
										String iap = utility.getIapProduct(citd.getListaProdotti()); 		
										citd.aggiungiProdotto(addProductField.getText(), iap,serialNumberProductField.getText(), typeProductField.getText(),brandProductField.getText(),null, typeProdField.getText(),Integer.valueOf(costProductField.getText()),dateProductField.getText(),linkProductField.getText());

										cl.show(mainPanel, "productSetupPanel");
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

							productLabel = new JLabel("Codice IAP");
							researchProductPanel.add(productLabel);

							researchProductField = new JTextField();
							researchProductPanel.add(researchProductField);
							researchProductField.setColumns(10);

							tableProductPanel.setLayout(new BoxLayout(tableProductPanel,BoxLayout.X_AXIS));

							try {
								String data[][] = null;
								data = utility.listToMatrixString(citd.getListaProdotti());
								String colonna[]={"NOME","IAP"};
								jtProduct = new JTable(data,colonna);
								tableProductPanel.add(new JScrollPane(jtProduct));

								jtProduct.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(final MouseEvent e){
										if (e.getClickCount() == 1){
											final JTable jTable= (JTable)e.getSource();
											final int row = jTable.getSelectedRow();
											// final int column = jTable.getSelectedColumn();
											valueInCell = (String)jTable.getValueAt(row, 1);

											//EVENTO MODIFICA
											if(!valueInCell.equalsIgnoreCase("") && userLogged != null && userLogged.getIsSuperuser() == true ) {
												editProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														final Prodotto prodotto = citd.getDetailsProdotto(valueInCell);
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
														nameProductField.setText(prodotto.getNome());

														JLabel iapProductLabel = new JLabel("IAP");
														editProductPanel.add(iapProductLabel);
														iapProductLabel.setPreferredSize(new Dimension (50,50));

														editIapProductField = new JTextField();
														editProductPanel.add(editIapProductField);
														editIapProductField.setColumns(10);
														editIapProductField.setText(prodotto.getIAP());


														JLabel numberProductLabel = new JLabel("Numero Seriale");
														numberProductLabel.setPreferredSize(new Dimension (50,50));
														editProductPanel.add(numberProductLabel);

														numberProductField = new JTextField();
														numberProductField.setBounds(300, 150, 144, 20);
														editProductPanel.add(numberProductField);
														numberProductField.setColumns(10);				
														numberProductField.setText(prodotto.getSerialNumber());


														JLabel typeProductLabel = new JLabel("Tipo");
														editProductPanel.add(typeProductLabel);
														typeProductLabel.setPreferredSize(new Dimension (50,50));

														editTypeProductField = new JTextField();
														editProductPanel.add(editTypeProductField);
														editTypeProductField.setColumns(10);
														editTypeProductField.setText(prodotto.getTipo());

														JLabel brandProductLabel = new JLabel("Marca");
														editProductPanel.add(brandProductLabel);
														brandProductLabel.setPreferredSize(new Dimension (50,50));


														editBrandProductField = new JTextField();
														editProductPanel.add(editBrandProductField);
														editBrandProductField.setColumns(10);
														editBrandProductField.setText(prodotto.getMarca());

														JButton editProductButton = new JButton("Salva");
														editProductPanel.add(editProductButton);
														editProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																citd.modificaProdotto(valueInCell, nameProductField.getText(), editIapProductField.getText(), numberProductField.getText(), editTypeProductField.getText(), editBrandProductField.getText());
															}
														});

														mainPanel.add(editProductPanel, "editProductPanel");
														cl.show(mainPanel, "editProductPanel");
													}
												});
											}

											if(!valueInCell.equalsIgnoreCase("") && userLogged != null && userLogged.getIsSuperuser() == true) {
												deleteProductBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {

														citd.eliminaProdotto(valueInCell);

														System.out.println("Elemento eliminato dal DB");
														try {
															String data[][] = null;
															data = utility.listToMatrixString(citd.getListaProdotti());
															String colonna[]={"NOME","IAP"};
															Model table = new Model(data,colonna);
															jtProduct.setModel(table);
															((Model) jtProduct.getModel()).fireTableDataChanged();

														}catch(Exception e) {
															System.out.println(e.getMessage());
														}

													}
												});
											}

											if (!valueInCell.equalsIgnoreCase(""))  {
												detailProductButton.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														detailProductPanel = new JPanel();
														backBtn = new JButton("Back");
														backBtn.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																cl.show(mainPanel, "viewProductPanel");
															}});
														backBtn.setBounds(85, 66, 72, 20);
														detailProductPanel.add(backBtn);
														final Prodotto prod = citd.getDetailsProdotto(valueInCell);
														if(prod!= null) {
															String colonna[]={"NOME","IAP","NUMERO SERIALE","TIPO", "MARCA", "UTENTE", "COSTO", "TIPO POSSESSO"};
															String data[][] = new String[1][8];
															data[0][0] = prod.getNome();
															data[0][1] = prod.getIAP();
															data[0][2] = prod.getSerialNumber();
															data[0][3] = prod.getTipo();
															data[0][4] = prod.getMarca();
															data[0][5] = prod.getUtente().getMatricola();
															data[0][6] =String.valueOf(prod.getCosto());
															data[0][7] = prod.getTipoPossesso();

															jtProduct = new JTable(data,colonna);
															detailProductPanel.add(new JScrollPane(jtProduct));
															Model table = new Model(data,colonna);
															jtProduct.setModel(table);
															((Model) jtProduct.getModel()).fireTableDataChanged();

															assignProductButton = new JButton("Assegna Prodotto");
															assignProductButton.setBounds(85, 106, 72, 20);
															if(userLogged.getIsSuperuser()) detailProductPanel.add(assignProductButton);

															assignProductButton.addActionListener(new ActionListener() {
																public void actionPerformed(ActionEvent arg0) {
																	JPanel assignUsersPanel = new JPanel();
																	detailProductPanel.add(assignUsersPanel);
																	backBtn = new JButton("Back");
																	backBtn.addActionListener(new ActionListener() {
																		public void actionPerformed(ActionEvent arg0) {
																			System.out.println("Prodotto assegnato all'utente");
																			cl.show(mainPanel, "detailProductPanel");
																		}});
																	backBtn.setBounds(85, 66, 72, 20);
																	assignUsersPanel.add(backBtn);
																	try {
																		String userdata [][]=utility.listToMatrixString(citd.getListaUtenti());
																		String column[]={"MATRICOLA"};
																		jt = new JTable(userdata,column); 
																		assignUsersPanel.add(new JScrollPane(jt));
																		assignBtn = new JButton("Assegna utente");
																		assignBtn.setBounds(85, 66, 72, 20);
																		assignUsersPanel.add(assignBtn);
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
																					if (!arrayString[0].equalsIgnoreCase("")){
																						assignBtn.addActionListener(new ActionListener() {
																							public void actionPerformed(ActionEvent arg0) {
																								
																								citd.assegnaUtente(prod, arrayString[0]);
																								cl.show(mainPanel, "viewProductPanel");
																							}});
																					}

																				}
																			}
																		});
																	}catch (Exception e) {
																		System.out.println(e.getMessage());
																	}
																	mainPanel.add(assignUsersPanel, "assignUsersPanel");
																	cl.show(mainPanel, "assignUsersPanel");
																}
															});

														}

														mainPanel.add(detailProductPanel, "detailProductPanel");
														cl.show(mainPanel, "detailProductPanel");
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
										Prodotto product = citd.getDetailsProdotto(researchProductField.getText());
										if (product != null) {
											found = true;
											System.out.println("Il prodotto che ho trovato e':" + product);
											String colonna[]={"NOME","IAP"};
											String data[][] = new String[1][2];
											data[0][0] = product.getNome();
											data[0][1] = product.getIAP();

											Model table = new Model(data,colonna);
											jtProduct.setModel(table);
											((Model) jtProduct.getModel()).fireTableDataChanged();
										}else {
											found=false;
											System.out.println("Non vi sono prodotti che corrispondono in DB");														
										}
									}
								});

								resetProductTableButton = new JButton("Reset");
								researchProductPanel.add(resetProductTableButton);
								resetProductTableButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										String colonna[]={"NOME","NUMERO SERIALE"};
										String data[][] = null;
										data = utility.listToMatrixString(citd.getListaProdotti());
										researchProductField.setText("");
										Model table = new Model(data,colonna);
										jtProduct.setModel(table);
										((Model) jtProduct.getModel()).fireTableDataChanged();
									}
								});
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}		

							detailProductButton = new JButton("Dettagli Prodotto");
							detailProductButton.setBounds(85, 106, 72, 20);
							tableProductPanel.add(detailProductButton);

							if(userLogged != null && userLogged.getIsSuperuser() == true ) {
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

					//GESTIONE MANUTENZIONE AMMINISTRATORE
					careProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							careProductPanel = new JPanel();
							careProductPanel.setLayout(new BoxLayout(careProductPanel, BoxLayout.Y_AXIS));

							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							careProductPanel.add(backBtn);
							String data[][]=citd.getAllMaintenanceProduct();
							String colonna[]={"NOME","IAP","STATO MANUTENZIONE"};
							jtProduct = new JTable(data,colonna);
							careProductPanel.add(new JScrollPane(jtProduct));

							jtProduct.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(final MouseEvent e){
									if (e.getClickCount() == 1){
										final JTable jTable= (JTable)e.getSource();
										final int row = jTable.getSelectedRow();			                
										valueInCell = (String)jTable.getValueAt(row, 1);
										String stato = (String)jTable.getValueAt(row, 2);
										if(!valueInCell.equalsIgnoreCase("") && stato.equalsIgnoreCase("In lavorazione")) {

											acceptMaintenanceBtn.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent arg0) {
													acceptMaintenanceBtn.removeActionListener(this);
													citd.setManutenzione(valueInCell, "approvata");
													citd.deleteMaintenanceRequest(valueInCell);
													String result[][] = citd.getAllMaintenanceProduct();							
													String colonna[]={"NOME","IAP","STATO MANUTENZIONE"};
													Model table = new Model(result,colonna);
													jtProduct.setModel(table);
													((Model) jtProduct.getModel()).fireTableDataChanged();
												}
											});
											denyMaintenanceBtn.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent arg0) {
													citd.setManutenzione(valueInCell,"rifiutata");
													citd.deleteMaintenanceRequest(valueInCell);
													String result[][] =citd.getAllMaintenanceProduct();
													String colonna[]={"NOME","IAP","STATO MANUTENZIONE"};
													Model table = new Model(result,colonna);
													jtProduct.setModel(table);
													((Model) jtProduct.getModel()).fireTableDataChanged();
												}
											});

											detailrequestBtn.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent arg0) {	
													JPanel detailRequestPanel = new JPanel();
													detailRequestPanel.setLayout(new BoxLayout(detailRequestPanel, BoxLayout.Y_AXIS));

													backBtn = new JButton("Back");
													backBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															cl.show(mainPanel, "careProductPanel");
														}
													});
													backBtn.setBounds(85, 66, 72, 20);
													detailRequestPanel.add(backBtn);
													mainPanel.add(detailRequestPanel, "detailRequestPanel");
													cl.show(mainPanel, "detailRequestPanel");
													String result[][] = citd.getCareRequest(valueInCell);
													String colonna[]={"IAP","PROBLEMA"};
													JTable jtDetail = new JTable(result,colonna);
													detailRequestPanel.add(new JScrollPane(jtDetail));
												}
											});

										}
									}
								}
							});

							detailrequestBtn = new JButton("Dettaglio Richiesta");
							careProductPanel.add(detailrequestBtn);

							acceptMaintenanceBtn = new JButton("Accetta Manutenzione");
							careProductPanel.add(acceptMaintenanceBtn);	

							denyMaintenanceBtn = new JButton("Rifiuta   Manutenzione");
							careProductPanel.add(denyMaintenanceBtn);	

							mainPanel.add(careProductPanel, "careProductPanel");
							cl.show(mainPanel, "careProductPanel");

						}
					});


					renewalLicenseButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							renewalLicensePanel = new JPanel();
							renewalLicensePanel.setLayout(new BoxLayout(renewalLicensePanel, BoxLayout.Y_AXIS));
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							renewalLicensePanel.add(backBtn);
							String colonna[]={"NOME","IAP","SERIAL NUMBER", "MARCA", "UTENTE", "SCADENZA"};
							String data[][]=utility.listToMatrixString(citd.getListaAbbonamenti());
							data = citd.reduceLicenseObject(data); 
							jtProduct = new JTable(data,colonna);
							renewalLicensePanel.add(new JScrollPane(jtProduct));
							jtProduct.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(final MouseEvent e){
									if (e.getClickCount() == 1){
										final JTable jTable= (JTable)e.getSource();
										final int row = jTable.getSelectedRow();			                
										valueInCell = (String)jTable.getValueAt(row, 1);
										if(!valueInCell.equalsIgnoreCase("")) {


											linkBtn.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													Abbonamento abb = citd.getAbbonamento(valueInCell);
													String url= abb.getUrl();
													scadenza.setVisible(true);
													scadenza.setSize(300, 20);
													//renewalLicensePanel.revalidate();
													renewalLicensePanel.updateUI();
													//renewalLicensePanel.validate();
													try {
														Desktop.getDesktop().browse(new URI(url));
														linkBtn.removeActionListener(this);
													} catch (IOException | URISyntaxException ex) {
														ex.printStackTrace();
													}
												}
											});
											if(!scadenza.getText().equalsIgnoreCase("")) {

												renewalBtn.addActionListener(new ActionListener() {
													@Override
													public void actionPerformed(ActionEvent e) {
														renewalBtn.removeActionListener(this);
														String controllo= citd.rinnovaScadenza(valueInCell, scadenza.getText());
														if(controllo == null) {
															scadenza.setText("Inserisci la scadenza corretta (dd/MM/yyyy)");
														}else {
															scadenza.setVisible(false);
															scadenza.setText("Inserisci la nuova scadenza (dd/MM/yyyy)");
															renewalLicensePanel.updateUI();
														}
														String result[][]=utility.listToMatrixString(citd.getListaAbbonamenti());
														String colonna[]={"NOME","IAP","SERIAL NUMBER", "MARCA", "UTENTE", "SCADENZA"};
														Model table = new Model(result,colonna);
														jtProduct.setModel(table);
														((Model) jtProduct.getModel()).fireTableDataChanged();	
														
													}
												});											
											}

										}
									}
								}
							});    	    

							linkBtn = new JButton("Aggiorna Licenza");
							renewalLicensePanel.add(linkBtn);

							scadenza = new JTextField("Inserisci la nuova scadenza (dd/MM/yyyy)");
							renewalLicensePanel.add(scadenza);
							scadenza.setVisible(false);

							renewalBtn = new JButton("Aggiornata");
							renewalLicensePanel.add(renewalBtn);	

							mainPanel.add(renewalLicensePanel, "renewalLicensePanel");
							cl.show(mainPanel, "renewalLicensePanel");

						}
					});

					//richiesta di manutenzione
					careRequestBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							careRequestPanel = new JPanel();
							careRequestPanel.setLayout(new BoxLayout(careRequestPanel, BoxLayout.Y_AXIS));
							careBtn = new JButton("Invia Segnalazione");
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							careRequestPanel.add(backBtn);

							String colonna[]={"NOME","IAP","STATO PRODOTTO","STATO RICHIESTA"};
							String data[][]=citd.getUserProduct(userLogged.getMatricola());


							jtProduct = new JTable(data,colonna);
							careRequestPanel.add(new JScrollPane(jtProduct));
							jtProduct.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(final MouseEvent e){
									if (e.getClickCount() == 1){
										final JTable jTable= (JTable)e.getSource();
										final int row = jTable.getSelectedRow();

										valueInCell  = (String)jTable.getValueAt(row, 1);
										String stato = (String)jTable.getValueAt(row, 2);
										final JTextField problem= new JTextField("Descrivi il problema del tuo dispositivo");
										if(!valueInCell.equalsIgnoreCase("") && !stato.equalsIgnoreCase("manutenzione")) {
											problemsBtn.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													JPanel problemsPanel = new JPanel();
													backBtn = new JButton("Back");
													backBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															cl.show(mainPanel, "careRequestPanel");
														}
													});
													backBtn.setBounds(85, 66, 72, 20);
													problem.setBounds(150, 66, 150, 20);
													problemsPanel.add(backBtn);
													problemsPanel.add(problem);	
													problemsPanel.add(careBtn);
													problemsBtn.removeActionListener(this);
													mainPanel.add(problemsPanel, "problemsPanel");
													cl.show(mainPanel, "problemsPanel");
												}
											});

											if(!problem.getText().equalsIgnoreCase("")) {
												careBtn.addActionListener(new ActionListener() {
													@Override
													public void actionPerformed(ActionEvent e) {
														citd.setProblemRequest(valueInCell, problem.getText());
														citd.setManutenzione(valueInCell, "In lavorazione");
														careBtn.removeActionListener(this);
														cl.show(mainPanel, "careRequestPanel");

														String colonna[]={"NOME","IAP","STATO PRODOTTO","STATO RICHIESTA"};
														String data[][]=citd.getUserProduct(userLogged.getMatricola());
														Model table = new Model(data,colonna);
														jtProduct.setModel(table);
														((Model) jtProduct.getModel()).fireTableDataChanged();
													}
												});
											}
										}
									}

								}
							});
							problemsBtn = new JButton("Segnala un problema");
							careRequestPanel.add(problemsBtn);	


							mainPanel.add(careRequestPanel, "careRequestPanel");
							cl.show(mainPanel, "careRequestPanel");

						}
					});


					newProductRequestBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							newProductRequestPanel = new JPanel();
							//newProductRequestPanel.setLayout(new BoxLayout(newProductRequestPanel, BoxLayout.Y_AXIS));
							newProductRequestPanel.setLayout(null);
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(1, 1, 80, 20);
							newProductRequestPanel.add(backBtn);
							JLabel nameProductLabel = new JLabel("Nome");
							nameProductLabel.setBounds(350, 250, 70, 15);
							newProductRequestPanel.add(nameProductLabel);

							nameProductField = new JTextField();  
							nameProductField.setBounds(420, 250, 144, 20);
							newProductRequestPanel.add(nameProductField);
							nameProductField.setColumns(10);

							JLabel typeProductLabel = new JLabel("Tipo");
							newProductRequestPanel.add(typeProductLabel);
							typeProductLabel.setBounds(350, 280, 70, 15);

							typeProductField = new JTextField();
							newProductRequestPanel.add(typeProductField);
							typeProductField.setColumns(10);
							typeProductField.setBounds(420, 280, 144, 20);

							JLabel brandProductLabel = new JLabel("Marca");
							newProductRequestPanel.add(brandProductLabel);
							brandProductLabel.setBounds(350, 310, 70, 15);

							brandProductField = new JTextField();
							newProductRequestPanel.add(brandProductField);
							brandProductField.setColumns(10);
							brandProductField.setBounds(420, 310, 144, 20);

							JLabel reasonProductLabel = new JLabel("Motivo");
							newProductRequestPanel.add(reasonProductLabel);
							reasonProductLabel.setBounds(350, 340, 70, 15);

							reasonProductField = new JTextField();
							newProductRequestPanel.add(reasonProductField);
							reasonProductField.setColumns(10);
							reasonProductField.setBounds(420, 340, 144, 20);

							JButton requestProductButton = new JButton("Salva");
							newProductRequestPanel.add(requestProductButton);
							requestProductButton.setBounds(435, 370, 80, 20);							
							requestProductButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if(!nameProductField.getText().equalsIgnoreCase("") && !typeProductField.getText().equalsIgnoreCase("") && !brandProductField.getText().equalsIgnoreCase("") && !reasonProductField.getText().equalsIgnoreCase("")) {
										String iap= utility.getIapProductRequest(citd.getListaRichiestaNuovoProdotto(true, userLogged.getMatricola()));
										citd.aggiungiRichiestaNuovoProdotto(iap,nameProductField.getText(),typeProductField.getText(),brandProductField.getText(),reasonProductField.getText(),userLogged.getMatricola());
										nameProductField.setText("");
										typeProductField.setText("");
										brandProductField.setText("");
										reasonProductField.setText("");				
									}
								}
							});
							mainPanel.add(newProductRequestPanel, "newProductRequestPanel");
							cl.show(mainPanel, "newProductRequestPanel");						
						}
					});

					//permette all'utente di visualizzare le richieste effettuate
					madeRequestBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							madeRequestPanel = new JPanel();
							madeRequestPanel.setLayout(new BoxLayout(madeRequestPanel, BoxLayout.Y_AXIS));
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							madeRequestPanel.add(backBtn);
							try {
								String data[][] = null;
								data = utility.listToMatrixString(citd.getListaRichiestaNuovoProdotto(userLogged.getIsSuperuser(), userLogged.getMatricola()));
								if(data != null) {
									String colonna[]={"IAP","UTENTE","NOME","TIPO","MARCA","DETTAGLI","STATO RICHIESTA"};
									jtProduct = new JTable(data,colonna);
									madeRequestPanel.add(new JScrollPane(jtProduct));
								}
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}

							mainPanel.add(madeRequestPanel, "madeRequestPanel");
							cl.show(mainPanel, "madeRequestPanel");						
						}
					});


					//permette all'amministratore di gestire le richieste effettuate
					managementRequestBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							managementRequestPanel = new JPanel();
							managementRequestPanel.setLayout(new BoxLayout(managementRequestPanel, BoxLayout.Y_AXIS));
							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							managementRequestPanel.add(backBtn);
							String data[][]=utility.listToMatrixString(citd.getListaRichiestaNuovoProdotto(userLogged.getIsSuperuser(), userLogged.getMatricola()));
							if(data != null) {
								String colonna[]={"IAP","UTENTE","NOME","TIPO","MARCA","DETTAGLI","STATO RICHIESTA"};
								jtProduct = new JTable(data,colonna);
								managementRequestPanel.add(new JScrollPane(jtProduct));
								jtProduct.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(final MouseEvent e){
										if (e.getClickCount() == 1){
											final JTable jTable= (JTable)e.getSource();
											final int row = jTable.getSelectedRow();			                
											valueInCell = (String)jTable.getValueAt(row, 0);
											String stato = (String)jTable.getValueAt(row, 6);
											//final List<RichiestaProdotto> richieste = citd.getListaRichiestaNuovoProdotto(userLogged.getIsSuperuser(), userLogged.getMatricola());
											if(!valueInCell.equalsIgnoreCase("") && stato.equalsIgnoreCase("In attesa di approvazione")) {
												acceptRequestBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														//final RichiestaProdotto req= new RichiestaProdotto(valueInCell);
														completeRequestPanel = new JPanel();
														//newProductRequestPanel.setLayout(new BoxLayout(newProductRequestPanel, BoxLayout.Y_AXIS));
														completeRequestPanel.setLayout(null);
														backBtn = new JButton("Back");
														backBtn.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																cl.show(mainPanel, "managementRequestPanel");
															}
														});
														backBtn.setBounds(1, 1, 80, 20);
														completeRequestPanel.add(backBtn);
														JLabel typeProductLabel = new JLabel("Tipologia");
														typeProductLabel.setBounds(350, 250, 70, 15);
														completeRequestPanel.add(typeProductLabel);
														typeProductField = new JTextField();  
														typeProductField.setBounds(420, 250, 144, 20);
														completeRequestPanel.add(typeProductField);
														typeProductField.setColumns(10);
														JLabel costProductLabel = new JLabel("Costo");
														completeRequestPanel.add(costProductLabel);
														costProductLabel.setBounds(350, 280, 70, 15);
														final JTextField costProductField = new JTextField();
														completeRequestPanel.add(costProductField);
														costProductField.setColumns(10);
														costProductField.setBounds(420, 280, 144, 20);
														JLabel dateProductLabel = new JLabel("Scadenza");
														completeRequestPanel.add(dateProductLabel);
														dateProductLabel.setBounds(350, 310, 70, 15);
														final JTextField dateProductField = new JTextField();
														completeRequestPanel.add(dateProductField);
														dateProductField.setColumns(10);
														dateProductField.setBounds(420, 310, 144, 20);
														JLabel linkProductLabel = new JLabel("Link");
														completeRequestPanel.add(linkProductLabel);
														linkProductLabel.setBounds(350, 340, 70, 15);
														final JTextField linkProductField = new JTextField();
														completeRequestPanel.add(linkProductField);
														linkProductField.setColumns(10);
														linkProductField.setBounds(420, 340, 144, 20);
														JButton requestProductButton = new JButton("Salva");
														completeRequestPanel.add(requestProductButton);
														requestProductButton.setBounds(435, 370, 80, 20);
														requestProductButton.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																if(!typeProductField.getText().equalsIgnoreCase("") && !costProductField.getText().equalsIgnoreCase("") 
																		&& !dateProductField.getText().equalsIgnoreCase("")	&& !linkProductField.getText().equalsIgnoreCase("") ){
																	citd.approvaRichiesta(valueInCell,typeProductField.getText(),costProductField.getText(), dateProductField.getText(),linkProductField.getText());
																	String result [][]=utility.listToMatrixString(citd.getListaRichiestaNuovoProdotto(userLogged.getIsSuperuser(), userLogged.getMatricola()));
																	String colonna[]={"IAP","UTENTE","NOME","TIPO","MARCA","DETTAGLI","STATO RICHIESTA"};
																	cl.show(mainPanel, "managementRequestPanel");
																	Model table = new Model(result,colonna);
																	jtProduct.setModel(table);
																	((Model) jtProduct.getModel()).fireTableDataChanged();
																}
															}
														});

														mainPanel.add(completeRequestPanel, "completeRequestPanel");
														cl.show(mainPanel, "completeRequestPanel");	
													}
												});
												denyRequestBtn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														citd.rifiutaRichiesta(valueInCell);
														String result [][]=utility.listToMatrixString(citd.getListaRichiestaNuovoProdotto(userLogged.getIsSuperuser(), userLogged.getMatricola()));
														String colonna[]={"IAP","UTENTE","NOME","TIPO","MARCA","DETTAGLI","STATO RICHIESTA"};
														//cl.show(mainPanel, "managementRequestPanel");
														Model table = new Model(result,colonna);
														jtProduct.setModel(table);
														((Model) jtProduct.getModel()).fireTableDataChanged();

													}
												});
											}
										}
									}
								});


							}
							acceptRequestBtn = new JButton("Accetta Richiesta");
							managementRequestPanel.add(acceptRequestBtn);	

							denyRequestBtn = new JButton("Rifiuta   Richiesta");
							managementRequestPanel.add(denyRequestBtn);


							mainPanel.add(managementRequestPanel, "managementRequestPanel");
							cl.show(mainPanel, "managementRequestPanel");						
						}
					});


					rentProductButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							rentProductPanel = new JPanel();
							rentProductPanel.setLayout(new BoxLayout(rentProductPanel, BoxLayout.Y_AXIS));

							backBtn = new JButton("Back");
							backBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									cl.show(mainPanel, "productSetupPanel");
								}
							});
							backBtn.setBounds(85, 66, 72, 20);
							rentProductPanel.add(backBtn);

							JLabel newScadenzaLabel = new JLabel("Nuova scadenza");

							newScadenzaField = new JTextField();

							String data[][] = citd.getTableNoleggi();
							String colonna[] = {"NOME","IAP","SCADENZA TRA"};
							jtProduct = new JTable(data,colonna);
							rentProductPanel.add(new JScrollPane(jtProduct));

							jtProduct.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(final MouseEvent e){
									if (e.getClickCount() == 1){
										final JTable jTable= (JTable)e.getSource();
										final int row = jTable.getSelectedRow();
										valueInCell = (String)jTable.getValueAt(row, 1);
										updateRentBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												if(newScadenzaField.getText() != "")
													citd.setScadenza(valueInCell, newScadenzaField.getText());

												String data[][] = citd.getTableNoleggi();
												String colonna[]={"NOME","IAP","SCADENZA TRA"};
												Model table = new Model(data,colonna);
												jtProduct.setModel(table);
												((Model) jtProduct.getModel()).fireTableDataChanged();
											}
										});
										openFileBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												System.out.println("open documento button");
												citd.apriDocumento(valueInCell);
											}
										});

										updateDocBtn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												JFileChooser fileChooser = new JFileChooser();
												int response = fileChooser.showSaveDialog(null);
												if(response == JFileChooser.APPROVE_OPTION){
													citd.aggiornaDocumento(valueInCell, fileChooser.getSelectedFile().getAbsolutePath(), "./documents");
												}
											}
										});
									}
								}
							});

							openFileBtn = new JButton("Apri documento");
							rentProductPanel.add(openFileBtn);	

							rentProductPanel.add(newScadenzaLabel);
							newScadenzaLabel.setPreferredSize(new Dimension (50,50));

							rentProductPanel.add(newScadenzaField);
							newScadenzaField.setColumns(10);

							updateRentBtn = new JButton("Aggiorna Scadenza");
							rentProductPanel.add(updateRentBtn);	

							updateDocBtn = new JButton("Aggiorna Documento");
							rentProductPanel.add(updateDocBtn);	

							mainPanel.add(rentProductPanel, "rentProductPanel");
							cl.show(mainPanel, "rentProductPanel");
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
					citd.saveAllInDB();
					userLogged = null;
					homeBar.setVisible(false);
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
