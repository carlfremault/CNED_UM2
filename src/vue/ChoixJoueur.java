package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * Frame du choix du joueur
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame implements Global {

	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	
	private Controle controle;
	
	private JLabel lblPersonnage;
	
	private int numeroPersonnage;

	
	

	/**
	 * Clic sur la fl�che "pr�c�dent" pour afficher le personnage pr�c�dent
	 */
	private void lblPrecedent_clic() {
		if (numeroPersonnage == 1) {
			numeroPersonnage = 3;
		} else {
			numeroPersonnage -= 1;
		}
		this.affichePerso();
	}
	
	/**
	 * Clic sur la fl�che "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		if (numeroPersonnage == NOMBREPERSONNAGES) {
			numeroPersonnage = 1;
		} else {
			numeroPersonnage += 1;
		}
		this.affichePerso();
	}
	
	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		if (this.txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocus();
		} else {
			controle.evenementChoixJoueur(this.txtPseudo.getText(), numeroPersonnage);
		}
	}

	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void affichePerso() {
		String chemin = CHEMINPERSONNAGES+PERSO+this.numeroPersonnage+MARCHE+1+"d"+1+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblPersonnage.setIcon(new ImageIcon(resource));	
	}
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		 
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(142, 115, 120, 120);
		contentPane.add(lblPersonnage);
		
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				sourisNormale();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				sourisNormale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				sourisNormale();
			}
		});
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = FONDCHOIX;
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		contentPane.add(lblFond);
		

		
		// positionnement sur la zone de saisie
		txtPseudo.requestFocus();
		
		// lien avec controleur
		this.controle = controle;
		
		// affichage Personnage
		this.numeroPersonnage = 1;
		this.affichePerso();

	}
}
