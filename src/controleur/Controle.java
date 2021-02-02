package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;

/**
 * Contr�leur et point d'entr�e de l'applicaton
 * 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {

	/**
	 * ecran entree jeu
	 */
	private EntreeJeu frmEntreeJeu;
	/**
	 * ecran arene
	 */
	private Arene frmArene;
	/**
	 * ecran choix joueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * jeu (type serveur ou client)
	 */
	private Jeu leJeu;

	/**
	 * M�thode de d�marrage
	 * 
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}

	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	/**
	 * evenement entrée jeu après clic sur Start (demarrage serveur) ou connect (demarrage client)
	 * @param info reçoit "serveur" pour demarrage serveur, adresse IP pour demarrage client
	 */
	public void evenementEntreeJeu(String info) {
		if (info.equals(SERVEUR)) {
			// Lancer serveur à l'écoute des clients qui veulent se connecter
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			// Creer JeuServeur
			this.leJeu = new JeuServeur(this);
			// Fermer fenêtre Entree et afficher Arene
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			// construction murs
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		} else {
			// Lancer demande de connection au serveur
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}

	/**
	 * evenement creation de joueur 
	 * @param pseudo pseudo choisi
	 * @param numeroPersonnage personnage choisi
	 */
	public void evenementChoixJoueur(String pseudo, int numeroPersonnage) {
		// envoi infos joueur vers serveur
		String envoiJoueur = PSEUDO + STRINGSEPARE + pseudo + STRINGSEPARE + numeroPersonnage;
		((JeuClient) leJeu).envoi(envoiJoueur);
		// affichage vues
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
	}

	/**
	 * demande du JeuServeur
	 * @param ordre ordre à executer
	 * @param info information à traiter
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch(ordre) {
		case AJOUTMUR :
			frmArene.ajoutMurs(info);
			break;
		case AJOUTPANELMURS :
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnMurs());
			break;
		}
	}
	
	public void evenementJeuClient(String ordre, Object info) {
		switch(ordre ) {
		case AJOUTPANELMURS :
			this.frmArene.setJpnMurs((JPanel)info);
			break;
		}
	}
	/**
	 * reception de communications
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case CONNEXION:
			if (!(this.leJeu instanceof JeuServeur)) {
				// Creer JeuClient
				this.leJeu = new JeuClient(this);
				// Memoriser objects connection
				this.leJeu.connexion(connection);
				// affichages vues
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION:
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION:

			break;
		}
	}

	/**
	 * envoi de connections
	 * @param connection où envoyer
	 * @param infosAEnvoyer information à envoyer
	 */
	public void envoi(Connection connection, Object infosAEnvoyer) {
		connection.envoi(infosAEnvoyer);
	}

}
