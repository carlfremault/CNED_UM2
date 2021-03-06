package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu {
	
	private Connection connection;
	
	/**
	 * Controleur
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
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 */
	public void envoi(String infosAEnvoyer) {
		super.envoi(this.connection, infosAEnvoyer);
	}

}
