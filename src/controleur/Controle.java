package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
	private static final int PORT = 6666;

	/**
	 * M�thode de d�marrage
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	public void evenementEntreeJeu(String info) {
		if (info == "serveur") {
			this.typeJeu = "serveur";
			// Lancer serveur à l'écoute des clients qui veulent se connecter
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			// Fermer fenêtre Entree et afficher Arene
			this.frmEntreeJeu.setVisible(false);
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		} else {
			this.typeJeu = "client";
			// Lancer demande de connection au serveur
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}
	
	public void evenementChoixJoueur(String pseudo, int numeroPersonnage) {
		this.frmChoixJoueur.setVisible(false);
		this.frmArene.setVisible(true);
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch(ordre) {
		case "connexion" :
			if (this.typeJeu=="client") {
				this.frmEntreeJeu.setVisible(false);
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
			break;
		case "réception" :
			
			break;
		case "déconnexion" :
			
			break;
		}
	}

}
