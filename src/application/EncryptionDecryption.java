package application;

public class EncryptionDecryption {
	
	private EncryptionDecryption() {	
	}
	
	/**
	 * A function which encrypts a plaintext given the parameters.
	 * @param grid PolybiusSquare
	 * @param key of any length
	 * @param plaintext with or without spaces and with our without capitals
	 * @return ciphertext
	 */
	public static String Encrypt(PolybiusSquare grid, String key, String plaintext) {
		
		plaintext = plaintext.replaceAll("\\s+",""); // removes all the white spaces.
		plaintext = plaintext.toUpperCase();
		
		key = key.toUpperCase();
		
		String ciphertext = "";
		
		for(int i = 0; i < plaintext.length(); i++) {
			int plaintextInNumbers = grid.getValueForLetter(plaintext.charAt(i));
			
			int indexKey = i % key.length();
			int keyInNumbers = grid.getValueForLetter(key.charAt(indexKey));
			
			int cipherNumber = (plaintextInNumbers + keyInNumbers) % 100;
			
			ciphertext += cipherNumber + " ";
		}
		
		return ciphertext;
	}
	
	/**
	 * A function which decrypts a ciphertext given the parameters.
	 * @param grid PolybiusSquare
	 * @param key of any length
	 * @param ciphertext with or without spaces
	 * @return plaintext
	 */
	public static String Decrypt(PolybiusSquare grid, String key, String ciphertext) {
		
		ciphertext = ciphertext.replaceAll("\\s+","");
		
		key = key.toUpperCase();
		
		String plaintext = "";
		
		for(int i = 0; i < ciphertext.length() / 2; i++) {
			int cipherNumber = (ciphertext.charAt(2 * i) - '0') * 10 + ciphertext.charAt(2 * i  + 1) - '0';
			if(cipherNumber < 11)
				cipherNumber += 100;
			
			int indexKey = i % key.length();
			int keyInNumbers = grid.getValueForLetter(key.charAt(indexKey));
			
			int plaintextInNumbers = cipherNumber - keyInNumbers;
			
			plaintext += grid.getLetterByValue(plaintextInNumbers);
		}
		
		
		return plaintext;
	}
	
}
