package Component;

import java.util.Random;
import java.util.Scanner;

public class HumanMode {

	// Test si Fin du jeu
	public Boolean EndGame = false;

	// Les valeurs pour le jeu
	String PlayerValue;
	String IaValue;

	public HumanMode() {
		// TODO Auto-generated constructor stub
	}

	// Créer une valeur à 4 chiffre défini par le joueur
	private String GeneratePlayerNumber() {

		System.out.println("Veuillez écrire un nombre à 4 chiffre entre 0000 et 9999");
		Scanner sc = new Scanner(System.in);
		String stockNumber = sc.nextLine();
		PlayerValue = stockNumber;

		return stockNumber;
	}

	// Créer une valeur aléatoire à 4 chiffre
	private String RandomValue() {

		// On ajoute une valeur aléatoire entre 0 et 9
		int min = 0;
		int max = 9;

		Random random = new Random();

		Integer value1 = random.nextInt(max + min);
		Integer value2 = random.nextInt(max + min);
		Integer value3 = random.nextInt(max + min);
		Integer value4 = random.nextInt(max + min);

		String Rvalue = value1.toString() + value2.toString() + value3.toString() + value4.toString();
		IaValue = Rvalue;

		return Rvalue;

	}
	
	public int ConversionSearchValue(String s1, String s2) {
		Integer nbPlayer1 = Integer.parseInt(s1.substring(0, 1));
		Integer nbPlayer2 = Integer.parseInt(s1.substring(1, 2));
		Integer nbPlayer3 = Integer.parseInt(s1.substring(2, 3));
		Integer nbPlayer4 = Integer.parseInt(s1.substring(3));
		
		Integer nbIa1 = Integer.parseInt(s2.substring(0, 1));
		Integer nbIa2 = Integer.parseInt(s2.substring(1, 2));
		Integer nbIa3 = Integer.parseInt(s2.substring(2, 3));
		Integer nbIa4 = Integer.parseInt(s2.substring(3));
		
		int NombrePlayerConvert[] = {nbPlayer1, nbPlayer2, nbPlayer3, nbPlayer4};
		int NombreIaConvert[] = {nbIa1, nbIa2, nbIa3, nbIa4};
		int Guess[] = new int[4];
		Integer tabGuess[] = new Integer[4];
		
		int firstElem = 0;
		int lastElem = 9;
		int milieu = (firstElem + lastElem) / 2;
		
		//Recherche dichotomique
		for (int i = 0; i < NombrePlayerConvert.length; i++) {
			if(NombrePlayerConvert[i] > NombreIaConvert[i]) {
				System.out.println("+");
				Guess[i] = milieu + 1;
			} 
			else if(NombrePlayerConvert[i] < NombreIaConvert[i]) {
				System.out.println("-");
				Guess[i] = milieu - 1;
			}
			else {
				System.out.println("=");
				Guess[i] = milieu;
			}
		}
		
		//TODO le tableau est constament remis à 0
		for (int i = 0; i < Guess.length; i++) {
			if(Guess[i] == NombrePlayerConvert[i]) {
				System.out.println("Votre nombre est : " + PlayerValue);
				System.out.println("Un chiffre trouvé : " + Guess[i]);	
			}
			
			if(tabGuess[i] == null) {
				tabGuess[i] = Guess[i];					
			}
			else if(tabGuess[i] != null) {
				tabGuess[i] = tabGuess[i];	
			}
			System.out.println("valeur tab : " + tabGuess[i]);
		}
		
		System.out.println(nbPlayer1);
		System.out.println(nbPlayer2);
		System.out.println(nbPlayer3);
		System.out.println(nbPlayer4);
		
		return nbPlayer1;
		
	}
	

	public void startGame(int NbEssai, int maxLife) {

		System.out.println("Veuillez concevoir un nombre mystère...");
		GeneratePlayerNumber();
		System.out.println("Votre nombre est : " + PlayerValue);
		System.out.println("------------------------------------------------------");

		for (int i = 0; i <= maxLife; i++) {
			System.out.println("L'ordinateur en est a son " + (NbEssai + i) + " essais");
			System.out.println("Il tente de découvrir votre nombre mystère");
			RandomValue();
			System.out.println(IaValue);
			ConversionSearchValue(PlayerValue, IaValue);
			System.out.println("------------------------------------------------------");

			if (IaValue.equals(PlayerValue) == true) {
				System.out.println("L'ordinateur à réussi à trouver votre nombre mystère qui était : " + PlayerValue);
				EndGame = true;
			} else if (NbEssai + i == 10) {
				System.out.println(
						"Quelle chance ! L'ordinateur n'a pas trouvé votre nombre mystère qui était : " + PlayerValue);
				EndGame = true;
			} else {

			}
		}

	}

}
