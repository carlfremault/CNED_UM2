package controleur;

import vue.EntreeJeu;

// Test nouvelle branche

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle {

	private EntreeJeu frmEntreeJeu ;

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
		this.frmEntreeJeu = new EntreeJeu() ;
		this.frmEntreeJeu.setVisible(true);
	}

}
