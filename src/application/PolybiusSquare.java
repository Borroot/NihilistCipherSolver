package application;

public class PolybiusSquare {
	
	
	private final int KEYWORD_LENGTH;
	
	private char[] keyword;
	private char[][] grid = new char[5][5];
	
	/**
	 * Initialize the keyword.
	 * @param keywordLength is the length of the keyword.
	 */
	public PolybiusSquare(int keywordLength) {
		
		if(keywordLength != 5) {
			System.out.println("Keyword lengths other than 5 are not supported (yet)");
			System.exit(0);
		}
		
		KEYWORD_LENGTH = keywordLength;
		
		initializeKeyword();
	
	}
	
	/**
	 * Sets the initial value of the keyword to EDCBA.
	 */
	private void initializeKeyword() {
		
		keyword = new char[KEYWORD_LENGTH];
		
		for(int i = 0; i < KEYWORD_LENGTH; i++)
			keyword[i] = (char) ('E' - i);
	}
	
	/**
	 * Check if the keyword contains any letters more than once.
	 * @param keyword
	 * @return true if the keyword is valid
	 */
	public static boolean isValidKeyword(String keyword) {
		
		keyword = keyword.replaceAll("\\s+","");
		keyword = keyword.toUpperCase();
		char[] keywordArray = new char[keyword.length()];
		
		for(int i = 0; i < keywordArray.length; i++) 
			keywordArray[i] = keyword.charAt(i);
		
		for(int i = 0; i < keyword.length(); i++) 
			for(int j = i + 1; j < keyword.length(); j++)
				if(keywordArray[i] == keywordArray[j]) {
					return false;
				}
		
		return true;
	}
	
	/**
	 * Checks if the keyword contains any letters more than once.
	 * @return if the keyword is valid.
	 */
	private boolean validKeyword() {
		
		for(int i = 0; i < KEYWORD_LENGTH; i++) 
			for(int j = i + 1; j < KEYWORD_LENGTH; j++)
				if(keyword[i] == keyword[j]) {
					return false;
				}
					
		
		return true;
	}
	
	/**
	 * Changes the keyword to the next valid keyword.
	 * This method can be used for iterating through all the possible keywords.
	 */
	public void nextKeyword() {
		
		do {
			int max = 0;
			
			for(int i = 0; i < KEYWORD_LENGTH; i++) {
				if(keyword[i] < 'Y') {
					keyword[i]++;
					max = i;
					break;
				}
			}
			
			for(int j = 0; j < max; j++)
				keyword[j] = 'A';
			
			if(max == KEYWORD_LENGTH - 1) {
				System.out.println("Progress: " + this.keyword[KEYWORD_LENGTH - 1]);
				for(int i = 0; i < KEYWORD_LENGTH; i++)
					System.out.print(keyword[i]);
				System.out.print("\n");
			}
				
			
		}while(!validKeyword());
	}
	
	/**
	 * Change the keyword to the given keyword. 
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		keyword = keyword.toUpperCase();
		for(int i = 0; i < KEYWORD_LENGTH; i++)
			this.keyword[i] = keyword.charAt(i);
	}
	
	/**
	 * Checks if a letter is already in the grid or not.
	 * @param letter
	 * @return if a letter is already in the grid.
	 */
	private boolean letterAlreadyUsed(char letter) {
		
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				if(letter == grid[i][j])
					return true;
		
		return false;
	}
	
	/**
	 * Fill in the grid using the given key;
	 */
	public void fillGrid() {
		
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				grid[i][j] = 'Z';
		
		for(int i = 0; i < grid[0].length; i++)
			grid[0][i] = keyword[i];
		
		char letter = 'A';
		
		for(int y = 1; y < grid.length; y++)
			for(int x = 0; x < grid[y].length; x++) {
					while(letterAlreadyUsed(letter))
						letter++;
					grid[y][x] = letter;
				}
		
	}
	
	/**
	 * Print the grid in the terminal.
	 */
	public void printGrid() {
		
		System.out.print("   ");
		for(int i = 0; i < grid[0].length; i++)
			System.out.print((i + 1) + "  ");
		
		for(int y = 0; y < grid.length; y++) {
			System.out.print("\n" + (y + 1) + "  ");
			for(int x = 0; x < grid[y].length; x++) 
				System.out.print(grid[y][x] + "  ");
		}
		
		System.out.println("\n");
	}
	
	/**
	 * Return the value by a given letter.
	 * @param letter in the grid
	 * @return a number in the domain 11-55
	 */
	public int getValueForLetter(char letter) {
		
		for(int y = 0; y < grid.length; y++)
			for(int x = 0; x < grid[y].length; x++){
				if(grid[y][x] == letter)
					return (y + 1) * 10 + (x + 1);
			}
		
		System.out.println("No value has been found for this letter: " + letter);
		System.exit(1);
		return 0;
	}
	
	/**
	 * Checks if a given value is valid.
	 * @param value
	 * @return if the value is valid
	 */
	public static boolean isValidValue(int value) {
		int row = value / 10 - 1;
		int col = value % 10 - 1;
		
		if(row > 4 || col > 4) {
			return false;
		}else if(row < 0 || col < 0) {
			return false;
		}else
			return true;
	}
	
	/**
	 * Return the letter by the given value.
	 * @param a number in the domain 11-55
	 * @return letter in the grid at the given value
	 */
	public char getLetterByValue(int value) {
		
		int row = value / 10 - 1;
		int col = value % 10 - 1;
		
		return grid[row][col];
	}
	
	public String getKeyword() {
		String keyword = "";
		
		for(int i = 0; i < KEYWORD_LENGTH; i++)
			keyword += this.keyword[i];
		
		return keyword;
	}

}

































