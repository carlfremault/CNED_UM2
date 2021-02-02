package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Informations et m�thodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {

	/**
	 * R�ception d'une connexion (pour communiquer avec un ordinateur distant)
	 * @param connection objet de connexion
	 */
	public abstract void connexion(Connection connection);

	/**
	 * R�ception d'une information provenant de l'ordinateur distant
	 * @param connection objet d'ou vient l information
	 * @param info information à traiter
	 */
	public abstract void reception(Connection connection, Object info);

	/**
	 * D�connexion de l'ordinateur distant
	 */
	public abstract void deconnexion();

	protected Controle controle;

	/**
	 * Envoi d'une information vers un ordinateur distant
	 * @param connection objet de connexion où envoyer
	 * @param infosAEnvoyer information à envoyer
	 */
	public void envoi(Connection connection, Object infosAEnvoyer) {
		this.controle.envoi(connection, infosAEnvoyer);
	}

}
