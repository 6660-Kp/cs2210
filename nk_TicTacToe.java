
public class nk_TicTacToe {
	char[][] gameBoard;
	int board_size;
	int inline;
	
	//Constructor
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		this.inline=inline;
		this.board_size = board_size;
		gameBoard = new char[board_size][board_size];
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size;j++) {
				gameBoard[i][j]=' ';
			}
		}
	}
	//returns an empty Dictionary of the size that you have selected
	public Dictionary createDictionary() {
		Dictionary myDic = new Dictionary(7349);
		return myDic;
	}
	
/* If it is in the dictionary this
	method returns its associated score, otherwise it returns the value -1.*/
	public int repeatedConfig(Dictionary configurations) {
		String tempString = "";
		String tempChar; 
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size;j++) {
				tempChar = Character.toString(gameBoard[i][j]);
				tempString = tempString + tempChar; 
			}
		}
		return configurations.get(tempString);
	}
	
	/*This method first represents the content of gameBoard as a string as described above; 
	 * then it inserts this string
	 * and score in the configurations dictionary*/
	public void insertConfig(Dictionary configurations, int score) {
		String tempString = "";
		String tempChar; 
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size;j++) {
				tempChar = Character.toString(gameBoard[i][j]);
				tempString = tempString + tempChar; 
			}
		}
		Record record = new Record(tempString,score);
		configurations.insert(record);
		
	}
	
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col]=symbol;
	}
	/*Returns true if gameBoard[row][col] is ＊ ＊; otherwise it returns false*/
	public boolean squareIsEmpty (int row, int col) {
		if(gameBoard[row][col]==' ') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean wins (char symbol) {
		
		for (int i = 0;i<board_size;i++) {
			for (int j = 0; j<board_size;j++) {
				if (gameBoard[i][j]==symbol) {
					
					for (int x = 1; x<inline;x++) {
						if (i+x>board_size-1) {
							break;
						}
						if (gameBoard[i+x][j]!=symbol) {
							break;
						}
						if (x == inline-1) {
							return true;
						}
					}
					
					for (int x = 1; x<inline;x++) {
						if (j+x>board_size-1) {
							break;
						}
						if (gameBoard[i][j+x]!=symbol) {
							break;
						}
						if (x == inline-1) {
							return true;
						}
					}
					for (int x = 1; x<inline;x++) {
						if (j+x>board_size-1 || i+x>board_size-1) {
							break;
						}
						if (gameBoard[i+x][j+x]!=symbol) {
							break;
						}
						if (x == inline-1) {
							return true;
						}
					}
					for (int x = 1; x<inline;x++) {
						if (j-x<0||i+x>board_size-1) {
							break;
						}
						if (gameBoard[i+x][j-x]!=symbol) {
							break;
						}
						if (x == inline-1) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
		
		
	}
	
	public boolean isDraw() {
		for (int i = 0;i<board_size;i++) {
			for (int j=0;j<board_size;j++) {
				if (squareIsEmpty(i,j)==true) {
					return false;
				}
			}
		}
		if (wins('X')) {
			return false;
		}
		else if(wins('O')) {
			return false;
		}
		else {
			return true;
		}
	}
	/*
	 * 每 3, if the computer has won, i.e. there are k adjacent ＊O＊s in the same row, column, 
	 * or diagonal of gameBoard;
	 * 每 0, if the human player has won.
	 * 每 2, if the game is a draw, i.e. 
	 * there are no empty positions in gameBoard and no player has won.
	 * 每 1, if the game is still undecided, i.e. there are still 
	 * empty positions in gameBoard and no
player has won.*/
	public int evalBoard() {
		if(wins('O')) {
			return 3;
		}
		else if (wins('X')) {
			return 0;
		}
		else if(isDraw()) {
			return 2;
		}
		else {
			return 1;
		}
	}
}
