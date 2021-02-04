package controleur;

public interface Global {

	int PORT = 6666;

	int NOMBREPERSONNAGES = 3;

	/**
	 * Caractère de séparation dans un chemin de fichiers
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * Chemin du dossier des images de fonds
	 */
	String CHEMINFONDS = "fonds" + CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image de la boule
	 */
	String CHEMINBOULES = "boules" + CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image du mur
	 */
	String CHEMINMURS = "murs" + CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des images des personnages
	 */
	String CHEMINPERSONNAGES = "personnages" + CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des sons
	 */
	String CHEMINSONS = "sons" + CHEMINSEPARATOR;
	/**
	 * Chemin de l'image de fond de la vue ChoixJoueur
	 */
	String FONDCHOIX = CHEMINFONDS + "fondchoix.jpg";
	/**
	 * Chemin de l'image de fond de la vue Arene
	 */
	String FONDARENE = CHEMINFONDS + "fondarene.jpg";
	/**
	 * Extension des fichiers des images des personnages
	 */
	String EXTFICHIERPERSO = ".gif";
	/**
	 * Début du nom des images des personnages
	 */
	String PERSO = "perso";
	/**
	 * Chemin de l'image de la boule
	 */
	String BOULE = CHEMINBOULES + "boule.gif";
	/**
	 * Chemin de l'image du mur
	 */
	String MUR = CHEMINMURS + "mur.gif";
	/**
	 * état marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * état touché du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * état mort du personnage
	 */
	String MORT = "mort";
	/**
	 * Caractère de séparation dans les chaines transférées
	 */
	String STRINGSEPARE = "~";
	/**
	 * Message "connexion" envoyé par la classe Connection
	 */
	String CONNEXION = "connexion";
	/**
	 * Message "réception" envoyé par la classe Connection
	 */
	String RECEPTION = "reception";
	/**
	 * Message "déconnexion" envoyé par la classe Connection
	 */
	String DECONNEXION = "deconnexion";
	/**
	 * Message "pseudo" envoyé pour la création d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de départ pour tous les joueurs
	 */
	int MAXVIE = 10;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1;
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2;
	/**
	 * largeur arene
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur arene
	 */
	int HAUTEURARENE = 600;
	/**
	 * hauteur mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur mur
	 */
	int LARGEURMUR = 34;
	/**
	 * nombre de murs
	 */
	int NBMURS = 20;
	/**
	 * hauteur personnage
	 */
	int HAUTEURPERSO = 44;
	/**
	 * largeur du personnage
	 */
	int LARGEURPERSO = 39;
	/** 
	 * hauteur message personnage (pseudo et vie)
	 */
	int HAUTEURMESSAGE = 8;
	/**
	 * orientation gauche
	 */
	int GAUCHE = 0;
	/**
	 * orientation droite
	 */
	int DROITE = 1;
	/**
	 * message "serveur" pour serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * message "client" pour client
	 */
	String CLIENT = "client";
	/**
	 * message "ajout mur" pour ajouter murs
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * message "ajout panel murs" pour ajouter murs client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * message "ajout jlabel jeu"
	 */
	String AJOUTJLABELJEU = "ajout jlabel jeu";
	/**
	 * ordre pour modifier le panel du jeu dans l'aeène du client
	 */
	String MODIFPANELJEU = "modif panel jeu";
	/**
	 * message chat
	 */
	String CHAT = "chat";
	/** 
	 * message ajout phrase
	 */
	String AJOUTPHRASE = "ajout phrase";
	/**
	 * message modification chat
	 */
	String MODIFCHAT = "modif chat";
	/** 
	 * message action
	 */
	String ACTION = "action";
	/**
	 * valeur en pixels d'un pas
	 */
	int UNPAS = 10;
	/**
	 * nombre d'étapes
	 */
	int NBETAPES = 4;
}
