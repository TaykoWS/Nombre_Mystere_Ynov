package component;
import java.util.Random;
import java.util.Scanner;

public class HumanMode {

	// Test si Fin du jeu
	public Boolean EndGame = false;

	// Les valeurs pour le jeu
	String PlayerValue;
	String IaValue;
	String IaValuenew;
	String[] Guess = new String[4];

	public HumanMode() {
		// TODO Auto-generated constructor stub
	}

	// Créer une valeur à 4 chiffre défini par le joueur
	private String GeneratePlayerNumber() {

		System.out.println("Veuillez écrire un nombre à 4 chiffre entre 0000 et 9999");
		Scanner sc = new Scanner(System.in);
		String stockNumber = sc.nextLine();
		if(stockNumber.length()==4) {
			PlayerValue = stockNumber;
			return stockNumber;
		}
		else {
			System.out.println("Merci de saisir 4 chiffres.");
			return GeneratePlayerNumber();
		}
	}

	// Créer une valeur aléatoire à 4 chiffre
	private String RandomValue() {
		// On ajoute une valeur aléatoire entre 0 et 9
		int min = 0;
		int max = 9;
		//chiffres que l'ordinateur devine
		Integer value1;
		Integer value2;
		Integer value3;
		Integer value4;
		
		Random random = new Random();
		//si l'ordi a lancé le random au moins une seule fois
		if(IaValue!=null) {

			Integer c1 =Integer.parseInt(IaValue.substring(0, 1));
			Integer c2= Integer.parseInt(IaValue.substring(1, 2));
			Integer c3= Integer.parseInt(IaValue.substring(2, 3));
			Integer c4= Integer.parseInt(IaValue.substring(3));
			
			int[] IaValueList = {c1,c2,c3,c4};
			for (int i = 0; i < Guess.length; i++) {
				// si chiffre deviné est supérieur que chiffre mystère
				if(Guess[i]=="+" ) {
					//random va parcourir de chiffre déviné -> chiffre max
					IaValueList[i]=random.nextInt((max - IaValueList[i]) + 1) + IaValueList[i];
					
				}
				//  si chiffre deviné est inférieur que chiffre mystère
				else if(Guess[i]=="-") {
					//random va parcourir de chiffre déviné -> chiffre min
					IaValueList[i]=random.nextInt((IaValueList[i] - min) + 1) + min;
				}
				// si chiffre mystère = chiffre mystère
				else {
					IaValueList[i]=IaValueList[i];
				}
			}
			IaValue= String.valueOf(IaValueList[0]) +String.valueOf(IaValueList[1])+String.valueOf(IaValueList[2])+String.valueOf(IaValueList[3]);
		}	
		//si l'ordi commence à deviner
		else {
			value1 = random.nextInt((max - min) + 1) + min;
			value2 = random.nextInt((max - min) + 1) + min;
			value3 = random.nextInt((max - min) + 1) + min;
			value4 = random.nextInt((max - min) + 1) + min;
			IaValue = value1.toString() + value2.toString() + value3.toString() + value4.toString();
		}

		return IaValue;

	}
	
	public String[] ConversionSearchValue(String s1, String s2) {
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
		//Recherche dichotomique
		for (int i = 0; i < NombrePlayerConvert.length; i++) {
			if(NombrePlayerConvert[i] != NombreIaConvert[i]) {
				if(NombrePlayerConvert[i] > NombreIaConvert[i]) {
					Guess[i]="+";
				} 
				else if(NombrePlayerConvert[i] < NombreIaConvert[i]) {
					Guess[i]="-";
				}
			}
			else {
				Guess[i] = String.valueOf(NombrePlayerConvert[i]);
			}
		}
		//afficher la suggestion
		IaValuenew= Guess[0]+Guess[1]+Guess[2]+Guess[3];
		System.out.println("Il tente de découvrir votre nombre mystère");
		System.out.println(IaValuenew);
		return Guess;
		
	}
	

	public void startGame(int NbEssai, int maxLife) {

		System.out.println("Veuillez concevoir un nombre mystère...");
		GeneratePlayerNumber();
		System.out.println("Votre nombre est : " + PlayerValue);
		System.out.println("------------------------------------------------------");

		for (int i = 1; i <= maxLife; i++) {
			System.out.println("L'ordinateur en est a son " + (NbEssai + i) + " essais");
			RandomValue();
			ConversionSearchValue(PlayerValue, IaValue);
			System.out.println("------------------------------------------------------");

			if (IaValue.equals(PlayerValue) == true) {
				System.out.println("L'ordinateur à réussi à trouver votre nombre mystère qui était : " + PlayerValue);
				maxLife=0;
				EndGame = true;
			} else if (NbEssai + i == 10) {
				System.out.println("Quelle chance ! L'ordinateur n'a pas trouvé votre nombre mystère qui était : " + PlayerValue);
				maxLife=0;
				EndGame = true;	
			}
		}

	}

}