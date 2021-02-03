package vue;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;


/**
 * frame de l'ar�ne du jeu
 * 
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {
	
	/**
	 * controleur
	 */
	private Controle controle;
	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	/**
	 * Panel murs
	 */
	private JPanel jpnMurs;
	/**
	 * Panel joueurs, boules
	 */
	private JPanel jpnJeu;
	/**
	 * Zone de saisie du chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du chat
	 */
	private JTextArea txtChat;

	/**
	 * ajout joueurs, boules
	 * @param jLabel le jLabel à insérer
	 */
	public void ajoutJLabelJeu(JLabel jLabel) {
		jpnJeu.add(jLabel);
		jpnJeu.repaint();
	}	
	/**
	 * getter for panel Jeu (joueurs et boules)
	 * @return le panel
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}	
	/**
	 * setter for panel Jeu (joueurs et boules)
	 * @param jpnJeu le panel
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}
	/**
	 * ajout murs
	 * @param unMur mur à ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	/**
	 * @return jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	/**
	 * @param jpnMurs panel a ajouter
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * getter pour texte dans txtChat
	 * @return texte dans txtChat
	 */
	public String getTxtChat() {
		return this.txtChat.getText();
	}
	/**
	 * setter pour txtChat
	 * @param txtChat texte
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
	}
	
	/**
	 * ajout d'un message dans le chat
	 * @param phrase à ajouter
	 */
	public void ajoutChat (String phrase) {
//		this.txtChat.append(phrase);
//		this.txtChat.append("\r\n");
		this.txtChat.setText(this.txtChat.getText()+phrase+"\r\n");
	}
	
	/**
	 * evenement touche Entree après avoir saisi un message
	 * @param e la touche appuyée
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		if (e.getKeyCode() ==  KeyEvent.VK_ENTER) {
			System.out.println("enter pressed");
			if (!this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(txtSaisie.getText());
				this.txtSaisie.setText("");
			}
		}
	}
	

	/**
	 * Create the frame.
	 */
	public Arene(Controle controle) {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(LARGEURARENE, HAUTEURARENE + 25 + 140));
		this.pack();
		// interdiction de changer la taille
		this.setResizable(false);

		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);

		txtSaisie = new JTextField();
		txtSaisie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSaisie_KeyPressed(e);
			}
		});
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);

		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);

		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);

		JLabel lblFond = new JLabel("");
		String chemin = FONDARENE;
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		this.controle = controle;

	}

}
