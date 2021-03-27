package Component;

import java.util.Random;
import java.util.Scanner;

public class IaMode {

	// Test si Fin du jeu
	public Boolean EndGame = false;

	// Les valeurs pour le jeu
	String IaValue;
	String PlayerValue;

	// On ajoute une valeur aléatoire entre 0 et 9
	int min = 0;
	int max = 9;

	Random random = new Random();

	Integer value1 = random.nextInt(max + min);
	Integer value2 = random.nextInt(max + min);
	Integer value3 = random.nextInt(max + min);
	Integer value4 = random.nextInt(max + min);

	public IaMode() {
		// TODO Auto-generated constructor stub
	}

	// Créer une valeur aléatoire à 4 chiffre
	private String RandomValue() {

		String Rvalue = value1.toString() + value2.toString() + value3.toString() + value4.toString();
		return Rvalue;

	}

	// Créer une valeur à 4 chiffre défini par le joueur
	private String GeneratePlayerNumber() {

		Scanner sc = new Scanner(System.in);
		String generateNumber = sc.nextLine();

		return generateNumber;
	}

	// Compare les valeurs de l'ordi et du joueur
	private void CompareValue(String s1, String s2) {
		Integer[] nb1 = new Integer[4];
		Integer[] nb2 = new Integer[4];
		char[] array1 = new char[4];
		char[] array2 = new char[4];

		for (int i = 0; i != array1.length; i++) {
			array1[i] = s1.charAt(i);
			array2[i] = s2.charAt(i);
			nb1[i] = Character.getNumericValue(array1[i]);
			nb2[i] = Character.getNumericValue(array2[i]);

			if (nb1[i] > nb2[i])
				System.out.print("+");
			else if (nb1[i] < nb2[i])
				System.out.print("-");
			else
				System.out.print("=");
		}
		System.out.println(" ");
	}

	public void startGame(int maxLife) {

		int NbEssai = 10;

		System.out.println("L'ordinateur a conçu un nombre mystère...");
		IaValue = RandomValue();
		//System.out.println(IaValue); --> Permet de debug pour gagner plus vite

		for (int i = 0; i <= NbEssai; i++) {
			System.out.println("Il vous reste " + (maxLife--) + " essais");
			System.out.println("Essayez de découvrir le nombre mystère");
			PlayerValue = GeneratePlayerNumber();

			if (PlayerValue.equals(IaValue) == true) {
				System.out.println("Félicitation le nombre mystère était bien : " + IaValue);
				NbEssai = 0;
				EndGame = true;
			} else if (maxLife == 0) {
				System.out.println("Dommage !! le nombre mystère était : " + IaValue);
				NbEssai = 0;
				EndGame = true;
			} else {
				CompareValue(IaValue, PlayerValue);
			}
		}
	}

}
