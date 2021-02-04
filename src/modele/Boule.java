package modele;

import java.net.URL;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable {

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur;
	/**
	 * collection des murs
	 */
	private Collection lesMurs;
	/**
	 * le joueur qui attaque
	 */
	private Joueur attaquant;
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		URL resource = getClass().getClassLoader().getResource(BOULE);
		super.jLabel.setIcon(new ImageIcon(resource));
		super.jLabel.setBounds(0, 0, LARGEURBOULE, HAUTEURBOULE);
	}

	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur unJoueur, Collection lesMurs) {
		this.attaquant = unJoueur;
		this.lesMurs = lesMurs;
		if (this.attaquant.getOrientation() == GAUCHE) {
			this.posX = (this.attaquant.getPosX() - LARGEURBOULE -1);
		} else {
			this.posX = (this.attaquant.getPosX() + LARGEURPERSO + 1);
		}
		this.posY = (this.attaquant.getPosY() + HAUTEURPERSO / 2);
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		this.attaquant.affiche(MARCHE, 1);
		super.jLabel.setVisible(true);
		Joueur victime = null;
		int lePas;
		if (this.attaquant.getOrientation() == GAUCHE) {
			lePas = -UNPAS;
		} else {
			lePas = UNPAS;
		}
		do {
			this.posX += lePas;
			jLabel.setBounds(posX, posY, LARGEURBOULE, HAUTEURBOULE);
			this.jeuServeur.envoiJeuATous();
			Collection lesJoueurs = this.jeuServeur.getJoueurs();
			victime = (Joueur)super.toucheCollectionObjets(lesJoueurs);
			
		} while ((posX >= 0) && (posX < LARGEURARENE - LARGEURBOULE) && victime == null && (this.toucheCollectionObjets(lesMurs) == null));
		if (victime != null && !victime.estMort()) {
			victime.perteVie();
			attaquant.gainVie();
			for (int i = 1; i <= NBETAPESTOUCHE; i++) {
				victime.affiche(TOUCHE, i);
				pause(80, 0);
			}
			if (victime.estMort()) {
				for (int i = 1; i <= NBETAPESMORT; i++) {
					victime.affiche(MORT, i);
					pause(80, 0);
				}
			} else {
				victime.affiche(MARCHE,  1);
			}
		}
		this.jLabel.setVisible(false);
		this.jeuServeur.envoiJeuATous();
	}
	
	private void pause(long milliSecondes, int nanoSecondes) {
		try {
			Thread.sleep(milliSecondes, nanoSecondes);
		} catch (InterruptedException e) {
			System.out.println("erreur pause;");
		}
	}
}
