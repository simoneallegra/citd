import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class App {
	
	private JFrame frame;
	private JPanel loginPanel, userSetupPanel;
	private JLabel matricolaLabel, passwordLabel;
	private JTextField matricolaField, passwordField;
	private JButton loginBtn;
	
	private JMenuBar homeBar;
	private JMenu setupMenu;
	private JMenuItem itemUserSetup;
	
	public CITD citd;
	
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
		System.out.println(utente.matricola);
		
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
				userSetupPanel.setLayout(null);
				
				JLabel provaLabel = new JLabel("prova");
				provaLabel.setBounds(10, 9, 65, 14);
				userSetupPanel.add(provaLabel);
				
				
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
