package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bram Pulles
 * This class handles everything regarding the words. 
 */

public class Words {
	
	private File wordFile = new File("resources/words.txt");
	private Scanner input;
	
	private final int KEYWORD_LENGTH;
	
	private ArrayList<String> allWords = new ArrayList<>();
	private ArrayList<String> possibleKeywords = new ArrayList<>();
	
	/**
	 * @param keywordLength for the length of the keyword
	 * The word file is read.
	 */
	public Words(int keywordLength) {
		
		KEYWORD_LENGTH = keywordLength;
		createWordList();
	}
	
	/**
	 * Creates a connection to the word file and saves the words in an ArrayList.
	 */
	private void createWordList() {
		
		try {
			input = new Scanner(wordFile);
		} catch (FileNotFoundException e) {
			System.out.println("The word file has not been found.");
		}
		
		while(input.hasNextLine()) {
			String word = input.nextLine();
			allWords.add(word);
			if(word.length() == KEYWORD_LENGTH)
				possibleKeywords.add(word);
		}
		
		input.close();
	}
	
	/**
	 * @return all the words in the wordfile.
	 */
	public ArrayList<String> getAllWords(){
		return allWords;
	}
	
	/**
	 * @return only the words with the length of the keyword
	 */
	public ArrayList<String> getPossibleKeywords(){
		return possibleKeywords;
	}
}


