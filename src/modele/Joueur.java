package modele;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo;
	/**
	 * getter pour pseudo
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * message perso (pseudo et vie)
	 */
	private JLabel messagePerso;
	/**
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur;
	/**
	 * num�ro d'�tape dans l'animation (de la marche, touch� ou mort)
	 */
	private int etape;
	/**
	 * la boule du joueur
	 */
	private Boule boule;
	/**
	 * vie restante du joueur
	 */
	private int vie;
	/**
	 * tourn� vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation;

	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position,
	 * affichage, cr�ation de la boule)
	 * @param pseudo pseudo du joueur
	 * @param numPerso numéro du personnage
	 */
	public void initPerso(String pseudo, int numPerso, ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		// System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
		super.jLabel = new JLabel();
		this.messagePerso = new JLabel();
		messagePerso.setHorizontalAlignment(SwingConstants.CENTER);
		messagePerso.setFont(new Font("Dialog", Font.PLAIN, 8));
		this.premierePosition(lesMurs, lesJoueurs);
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(messagePerso);
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre
	 * joueur ou un mur)
	 */
	private void premierePosition(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		super.jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			this.posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO));
			this.posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE));
			
		} while (this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}

	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES + PERSO + this.numPerso + etat + etape + "d" + orientation + EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		this.messagePerso.setBounds(posX - 10, posY + HAUTEURPERSO, LARGEURPERSO + 20, HAUTEURMESSAGE);
		this.messagePerso.setText(pseudo + " : " + vie);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * G�re une action re�ue et qu'il faut afficher (d�placement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * G�re le d�placement du personnage
	 */
	private void deplace() {
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * 
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for(Joueur unJoueur : lesJoueurs) {
			if (!this.equals(unJoueur)) {
				if (super.toucheObjet(unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Contr�le si le joueur touche un des murs
	 * 
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for(Mur leMur : lesMurs) {
			if (super.toucheObjet(leMur)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
	}

	/**
	 * Perte de points de vie apr�s avoir �t� touch�
	 */
	public void perteVie() {
	}

	/**
	 * vrai si la vie est � 0
	 * 
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}

	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
	}

}
