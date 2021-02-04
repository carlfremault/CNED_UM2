package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
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
	 * getter pour orientation
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}
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
	public void initPerso(String pseudo, int numPerso, Collection lesMurs, Collection lesJoueurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		// System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
		super.jLabel = new JLabel();
		this.messagePerso = new JLabel();
		messagePerso.setHorizontalAlignment(SwingConstants.CENTER);
		messagePerso.setFont(new Font("Dialog", Font.PLAIN, 8));
		this.boule = new Boule(this.jeuServeur);
		this.premierePosition(lesMurs, lesJoueurs);
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(messagePerso);
		this.jeuServeur.ajoutJLabelJeuArene(boule.getjLabel());
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre
	 * joueur ou un mur)
	 */
	private void premierePosition(Collection lesMurs, Collection lesJoueurs) {
		super.jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			this.posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO));
			this.posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE));
			
		} while (super.toucheCollectionObjets(lesJoueurs) != null || this.toucheCollectionObjets(lesMurs) != null);
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
	 * Gère action à effectuer (deplacement)
	 * 
	 * @param action     = l'action à effectuer
	 * @param lesJoueurs = collection des Joueurs
	 * @param lesMurs    = collection des Murs
	 */
	public void action(int action, Collection lesJoueurs, Collection lesMurs) {
		switch (action) {
		case KeyEvent.VK_LEFT:
			this.orientation = GAUCHE;
			this.posX = deplace(posX, action, -UNPAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_RIGHT:
			this.orientation = DROITE;
			this.posX = deplace(posX, action, UNPAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_UP:
			this.posY = deplace(posY, action, -UNPAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs,
					lesMurs);
			break;
		case KeyEvent.VK_DOWN:
			this.posY = deplace(posY, action, UNPAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_SPACE :
			if (!boule.getjLabel().isVisible()) {
				this.boule.tireBoule(this, lesMurs);
			};
			
			break;
		}
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Gère le déplacement du personnage
	 * 
	 * @param position = la position actuelle
	 * @param action = l'action (direction)
	 * @param lePas = nombre de pixels
	 * @param positionMax = position à ne pas dépasser (bord arene)
	 * @param lesJoueurs collection des joueurs
	 * @param lesMurs collection des murs
	 * @return
	 */
	private int deplace(int position,
			int action,
			int lePas, 
			int positionMax, 
			Collection lesJoueurs, 
			Collection lesMurs) {
		int anciennePosition = position;
		position += lePas;
		position = Math.max(position, 0);
		position = Math.min(position, positionMax);
		if (action==KeyEvent.VK_LEFT || action==KeyEvent.VK_RIGHT) {
			posX = position ;
		}else{
			posY = position ;
		}
		if (this.toucheCollectionObjets(lesJoueurs) != null || this.toucheCollectionObjets(lesMurs) != null) {
			position = anciennePosition;
		}
		etape = (etape % NBETAPES) + 1;
		return position;
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * 
	 * @return true si deux joueurs se touchent
	 */
//	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
//		for(Joueur unJoueur : lesJoueurs) {
//			if (!this.equals(unJoueur)) {
//				if (super.toucheObjet(unJoueur)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/**
	 * Contr�le si le joueur touche un des murs
	 * 
	 * @return true si un joueur touche un mur
	 */
//	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
//		for(Mur leMur : lesMurs) {
//			if (super.toucheObjet(leMur)) {
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
		this.vie += GAIN;
	}

	/**
	 * Perte de points de vie apr�s avoir �t� touch�
	 */
	public void perteVie() {
		this.vie = Math.max(this.vie - PERTE, 0);
	}

	/**
	 * vrai si la vie est � 0
	 * 
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return (this.vie == 0);
	}

	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
	}

}
