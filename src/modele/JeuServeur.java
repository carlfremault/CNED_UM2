package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>();
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();

	/**
	 * Constructeur
	 * @param controle instance du controleur
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String) info).split(STRINGSEPARE);
		String ordre = infos[0];
		switch (ordre) {
		case PSEUDO:
			super.controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			String pseudo = infos[1];
			int numPerso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, lesMurs, lesJoueurs.values());
			String phrase = "*** "+pseudo+" vient de se connecter ***";
			this.controle.evenementJeuServeur(AJOUTPHRASE, phrase);
			break;
		case CHAT :
			String message = infos[1];
			message = this.lesJoueurs.get(connection).getPseudo() + " > " + message;
			this.controle.evenementJeuServeur(AJOUTPHRASE, message);
			break;
		case ACTION :
			Integer action = Integer.parseInt(infos[1]);
			this.lesJoueurs.get(connection).action(action, this.lesJoueurs.values(), this.lesMurs);
			break;
		}
	}

	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients fais appel plusieurs fois �
	 * l'envoi de la classe Jeu
	 */
	public void envoi(Object info) {
		for(Connection connection : lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
	}

	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for (int i = 0; i < NBMURS; i++) {
			this.lesMurs.add(new Mur());
			controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
	}
	
	/**
	 * ajout label avec joueurs, ...
	 * @param jLabel label à envoyer
	 */
	public void ajoutJLabelJeuArene(JLabel jLabel) {
		controle.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}
	
	public void envoiJeuATous() {
		for(Connection connection : lesJoueurs.keySet()) { 
			this.controle.evenementJeuServeur(MODIFPANELJEU, connection);
		}
	}

}
