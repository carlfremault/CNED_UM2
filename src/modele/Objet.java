package modele;

import javax.swing.JLabel;

/**
 * Informations communes � tous les objets (joueurs, murs, boules) permet de
 * m�moriser la position de l'objet et de g�rer les collisions
 *
 */
public abstract class Objet {

	/**
	 * position X de l'objet
	 */
	protected Integer posX;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY;
	/**
	 * Jlabel de l'objet
	 */
	protected JLabel jLabel;
	
	public JLabel getjLabel() {
		return jLabel;
	}

	/**
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * 
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet(Objet objet) {
		if (this.jLabel != null && objet != null) {
			if (this.jLabel.contains(objet.posX, objet.posY) || 
					this.jLabel.contains((objet.posX + objet.jLabel.getWidth()), objet.posY + objet.jLabel.getHeight()) ||
					this.jLabel.contains(objet.posX, (objet.posY + objet.jLabel.getHeight())) ||
					this.jLabel.contains((objet.posX + objet.jLabel.getWidth()), objet.posY)) {
				return true;
				}
			}
		return false;
	}

}
