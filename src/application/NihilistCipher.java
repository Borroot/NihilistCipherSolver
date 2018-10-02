/**
 * 
 */
package application;

import java.util.ArrayList;

/**
 * @author Bram Pulles
 * This program can be used to find the keyword of a given encrypted text (in Nihilist cipher) and key. 
 * Two methods can be used: bruteforce all the possible keywords or bruteforce all the keywords which are 'real' words.
 * 
 * I used this program to decipher the following ciphertext:
 * "6559439645696558576646487955577762704969530352496958538583484849678645677526546472466727469663867859658376607655576676764758"
 * It was given that the keyword length is five so I bruteforced all the possible 'real' keywords with length five.
 * This resulted in the keyword: BREAK. And the following plaintext: 
 * "It is a mistake to think you can solve any major problems just with potatoes."
 *  
 */

public class NihilistCipher {
	
	private static final int KEYWORD_LENGTH = 5;
	private static final String CIPHERTEXT = 	"655943964569655857664648795557776270496953" + 
												"035249695853858348484967864567752654647246" + 
												"6727469663867859658376607655576676764758";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test();
		//checkAllWords();
		checkAllRealWords();
		
	}
	
	private static void test() {
		PolybiusSquare polybiusSquare = new PolybiusSquare(KEYWORD_LENGTH);
		polybiusSquare.setKeyword("Broed");
		polybiusSquare.fillGrid();
		polybiusSquare.printGrid();
		
		String ciphertext = EncryptionDecryption.Encrypt(polybiusSquare, "marvin", "Dit is een testgeval");
		System.out.println(ciphertext);
		System.out.println(EncryptionDecryption.Decrypt(polybiusSquare, "marvin", ciphertext));
		
	}
	
	/**
	 * Check all the possible keys with the correct length.
	 */
	private static void checkAllWords() {
		PolybiusSquare polybiusSquare = new PolybiusSquare(KEYWORD_LENGTH);
		
		while(polybiusSquare.getKeyword().compareTo("UVWXY") != 0) {
			
			polybiusSquare.fillGrid();
			
			String plaintext = EncryptionDecryption.Decrypt(polybiusSquare, "marvin", CIPHERTEXT);
			
			if(validAnswer(plaintext)) {
				polybiusSquare.printGrid();
				System.out.println(plaintext);
				//System.exit(0);
			}
			
			polybiusSquare.nextKeyword();	
		}
		
		System.out.println("No solutions have been found");
	}
	
	/**
	 * Check all the real words with the correct length.
	 */
	private static void checkAllRealWords() {
		Words words = new Words(KEYWORD_LENGTH);
		PolybiusSquare polybiusSquare = new PolybiusSquare(KEYWORD_LENGTH);
		
		ArrayList<String> possibleKeywords = words.getPossibleKeywords();
		
		for(int i = 0; i < possibleKeywords.size(); i++) {
			if(PolybiusSquare.isValidKeyword(possibleKeywords.get(i))) {
				polybiusSquare.setKeyword(possibleKeywords.get(i));
				polybiusSquare.fillGrid();
				
				String plaintext = EncryptionDecryption.Decrypt(polybiusSquare, "marvin", CIPHERTEXT);
				
				if(validAnswer(plaintext)) {
					polybiusSquare.printGrid();
					System.out.println(plaintext);
					System.exit(0);
				}
			}	
		}
		
		System.out.println("No solutions have been found");
	}
	
	/**
	 * Checks whether there are any letter which aren't translated in the answer.
	 * @param plaintext
	 * @return if a answer is valid
	 */
	private static boolean validAnswer(String plaintext) {
		
		//TODO: check how valid the answer is by searching for words in it.
		int originalLength = plaintext.length();
		plaintext = plaintext.replaceAll("_", "");
		
		for(int i = 0; i < plaintext.length(); i++)
			if(originalLength - plaintext.length() > 0)
				return false;
		
		return true;
	}
	
}
