import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class App {
	
	private JFrame frame;
	private JPanel loginPanel, userSetupPanel, userAddPanel;
	private JLabel matricolaLabel, passwordLabel, addLabel;
	private JTextField matricolaField, passwordField;
	private JButton loginBtn, addBtn;
	private JTable jt;
	
	private JMenuBar homeBar;
	private JMenu setupMenu;
	private JMenuItem itemUserSetup;
	
	public CITD citd;
	
	public CITDamministratore citdAmministratore;
	
	public App() {
		//initialize();
		home(new Utente("pippo"));
	}
	
	private void initialize() {
		frame = new JFrame("CITD");
		frame.setBounds(100, 100, 663, 420);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "loginPanel");
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
				home(userLogged);
				
			}
		});
		loginBtn.setBounds(85, 66, 72, 20);
		loginPanel.add(loginBtn);
		
	}
	
	private void home(Utente utente){
		
		// DA RIMUOVERE
		frame = new JFrame("CITD - " + utente.matricola);
		frame.setBounds(100, 100, 663, 420);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		// -----------
		
		homeBar = new JMenuBar();
		frame.setJMenuBar(homeBar);
				
		setupMenu = new JMenu("Gestione");
		homeBar.add(setupMenu);
		
		itemUserSetup = new JMenuItem("Gestione Utenti");
		itemUserSetup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// da rivedere funzionamento apertura panel
				
				userSetupPanel = new JPanel();
				frame.getContentPane().add(userSetupPanel, "userSetupPanel");
				userSetupPanel.setLayout(new BoxLayout(userSetupPanel, BoxLayout.X_AXIS));
				
				String data[][] ={{""}};
				try {
					
					BufferedReader br = new BufferedReader(new FileReader("./database/db_users.txt"));
					String s = "";
					int i = 0;
						while((s = br.readLine()) != null){
							data[i] = s.split(",");
							System.out.println(i);
							i = i +1;
						}
						
					
						br.close();
				}catch(Exception e) {
				}
				
				
				String column[]={"USERNAME"};
				jt = new JTable(data,column);    
			    jt.setBounds(30,40,100,100);            
			    userSetupPanel.add(new JScrollPane(jt));
				
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
						
						Utente newUser = new Utente(matricolaField.getText());
						citd = new CITD();
						citd.inserisciNuovoUtente(newUser, passwordField.getText());
						
						
					}
				});
				addBtn.setBounds(85, 66, 150, 20);
				userAddPanel.add(addBtn);
				
				userSetupPanel.add(userAddPanel);
				
				
			}
		});
		setupMenu.add(itemUserSetup);
		
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
