package Game;

import java.util.Scanner;

import Component.IaMode;

import Component.HumanMode;

public class ModeDeJeu {

	IaMode iamode = new IaMode();
	HumanMode hmode = new HumanMode();

	int vieJoueur = 10;
	int EssaiIA = 0;
	int IaLife = 10;

	public ModeDeJeu() {
		// TODO Auto-generated constructor stub
	}

	public void StartMenu() {

		int choice;

		do {
			System.out.println("Bienvenue sur le jeu 'Nombre Mystère'");
			System.out.println("--------------------------------------------");
			System.out.println("Veuillez choisir votre mode de jeu");
			System.out.println("1. IA VS HUMAIN");
			System.out.println("2. HUMAIN VS IA");
			System.out.println("3. Quitter l'appliation");

			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				// IA VS HUMAIN Mode :
				// L'ordinateur fait deviner un nombre à 4 chiffre au joueur, il a 10 chances
				// pour trouver la solution.
				iamode.startGame(vieJoueur);
				ReloadGame();
				break;
			case 2:
				// HUMAIN VS IA Mode :
				// Le joueur fait deviner un nombre à 4 chiffre à l'ordinateur, l'ordinateur à
				// 10 chances aussi.
				hmode.startGame(EssaiIA, IaLife);
				ReloadGame();
				break;
			case 3:
				System.out.println("Fin du programme");
				System.exit(0);
				break;
			default:
				System.out.println("Merci de saisir une valeur entre 1 et 3.");
				break;
			}

		} while (choice > 3 || choice == 0);

		if (iamode.EndGame == true) {
			ReloadGame();
		} else if (hmode.EndGame == true) {
			ReloadGame();
		}

	}

	private void ReloadGame() {

		int choice;

		do {
			System.out.println("Partie terminée ! Veuillez faire un nouveau choix : ");

			System.out.println("1. Rejouer");
			System.out.println("2. Changer de mode");
			System.out.println("3. Quitter l'appliation");

			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				RestartGame();
				break;
			case 2:
				StartMenu();
				break;
			case 3:
				System.out.println("Fin du programme");
				System.exit(0);
				break;
			default:
				System.out.println("Merci de saisir une valeur entre 1 et 3.");
				break;
			}

		} while (choice > 3 || choice == 0);
	}

	private void RestartGame() {

		if (iamode.EndGame == true) {
			// Rejoue mode IA
			hmode.EndGame = false;
			IaMode iamode = new IaMode();
			iamode.startGame(vieJoueur);
			ReloadGame();
		} else if (hmode.EndGame == true) {
			// Rejoue mode Humain
			iamode.EndGame = false;
			HumanMode hmode = new HumanMode();
			hmode.startGame(EssaiIA, IaLife);
			ReloadGame();
		}

	}

}
