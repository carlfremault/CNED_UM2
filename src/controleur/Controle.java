package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
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

	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
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

	public void evenementEntreeJeu(String info) {
		if (info == "serveur") {
			// Lancer serveur à l'écoute des clients qui veulent se connecter
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			// Creer JeuServeur
			this.leJeu = new JeuServeur(this);
			// Fermer fenêtre Entree et afficher Arene
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		} else {
			// Lancer demande de connection au serveur
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}

	public void evenementChoixJoueur(String pseudo, int numeroPersonnage) {
		// envoi infos joueur vers serveur
		String envoiJoueur = PSEUDO + STRINGSEPARE + pseudo + STRINGSEPARE + numeroPersonnage;
		((JeuClient) leJeu).envoi(envoiJoueur);
		// affichage vues
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
	}

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

	public void envoi(Connection connection, Object infosAEnvoyer) {
		connection.envoi(infosAEnvoyer);
	}

}
