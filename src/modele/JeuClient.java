package modele;

import javax.swing.JPanel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu implements Global {

	private Connection connection;
	
	/**
	 * booleen pour vérifier si panel des murs est bien reçu
	 */
	private boolean mursOk = false;

	/**
	 * Controleur
	 * @param controle instance du controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if (!mursOk) {
				this.controle.evenementJeuClient(AJOUTPANELMURS, info);
				mursOk = true;
			} else {
				this.controle.evenementJeuClient(MODIFPANELJEU, info);
			}
		} else if (info instanceof String) {
			this.controle.evenementJeuClient(MODIFCHAT, info);
		} else if (info instanceof Integer) {
			this.controle.evenementJeuClient(JOUESON, info);
		}
	}

	@Override
	public void deconnexion(Connection connection) {
		System.exit(0);
	}

	/**
	 * Envoi d'une information vers le serveur fais appel une fois � l'envoi dans la
	 * classe Jeu
	 * @param infosAEnvoyer information à envoyer
	 */
	public void envoi(String infosAEnvoyer) {
		super.envoi(this.connection, infosAEnvoyer);
	}

}
