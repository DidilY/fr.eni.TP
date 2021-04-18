package fr.eni.maximot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Maximot {
	public final static Scanner SCAN = new Scanner(System.in);
	public static void main(String[] args) {
		final String PATH = "dictionnaire.txt";
		String word = "";
		String setOfLetters = "";
		String input = "";
		
		word = getRandomWord(PATH);
		try {
			setOfLetters = shuffleLettersIn(word);
			input = askForInput(setOfLetters);
			printResult(input, setOfLetters, word);
		} catch (Exception e ) {
			System.err.println(e.getMessage());
		}
		SCAN.close();
	}
	
	private static void printResult(String input, String mixedWord, String word) {
		
	}
	
	private static String askForInput(String setOfLetters) {
		String input = "";
		System.out.println("Formez le mot le plus long possible avec le tirage de lettres suivant :");
		System.out.println(setOfLetters);
		System.out.println();
		System.out.println("-> À vous de jouer ! ");
		System.out.println();
		do {
			System.out.println("Écrivez un mot uniquement avec les lettres que vous avez tirées :");
			input = SCAN.nextLine();
		} while(!foundInSet(setOfLetters,input));
		return null;
	}
	
	private static boolean foundInSet(String setOfLetters, String input) {
		boolean found = false;
		int i = 0;
		do {
			found = (setOfLetters.indexOf(input.charAt(i)) >= 0);
			i++;
		} while(found && i<input.length());
		return found;
	}

	private static String shuffleLettersIn(String word) throws Exception {
		if(!word.isBlank()) throw new Exception("Erreur: le mot généré à partir du dictionnaire est vide. Impossible de lancer le jeu.");

		char letters[] = word.toCharArray();
		char temp;
		Random rand = new Random();
		
		for(int i = 0, j = 0; i<letters.length; i++) {
			j = rand.nextInt(letters.length);
			temp = letters[i];
			letters[i] = letters[j];
			letters[j] = temp;
		}
		return String.valueOf(letters);
	}
	
	private static String getRandomWord(String path) {
		String word = "";
		try(FileInputStream dictionnary = new FileInputStream(path)) {
			Scanner reader = new Scanner(dictionnary);
			int rand = new Random().nextInt(22507);
			for(int i = 0; i<rand; i++)
				reader.nextLine();
			word = reader.nextLine().toUpperCase();
		} catch(FileNotFoundException e) {
			System.err.println("Impossible de générer le mot demandé : le fichier " + path + " n'a pas été trouvé.");
		} catch(IOException e) {
			System.err.println("Impossible de générer le mot demandé : le fichier " + path + " n'a pas pu être lu.");
		}
		return word;
	}
}
