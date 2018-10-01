/**
 * 
 */
package application;

import java.util.ArrayList;

/**
 * @author Bram Pulles
 * This program can be used to find the keyword of a given encrypted text (in Nihilist cipher) and key. 
 *
 */
public class NihilistCipher {
	
	private static final int KEYWORD_LENGTH = 5;
	private static final String ciphertext = 	"655943964569655857664648795557776270496953" + 
												"035249695853858348484967864567752654647246" + 
												"6727469663867859658376607655576676764758";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Test");
		
		Words words = new Words(KEYWORD_LENGTH);
		PolybiusSquare polybiusSquare = new PolybiusSquare(KEYWORD_LENGTH);
		
		ArrayList<String> possibleKeywords = words.getPossibleKeywords();
		
		for(int i = 0; i < possibleKeywords.size(); i++) {
			
			// check if some keyword is valid
			// make a function to check which solution is the best (and which are gibberish)
			
			polybiusSquare.setKeyword(possibleKeywords.get(i));
			polybiusSquare.fillGrid();
			polybiusSquare.printGrid();
			System.out.println(EncryptionDecryption.Decrypt(polybiusSquare, "marvin", ciphertext));
		}

	}
	
}
