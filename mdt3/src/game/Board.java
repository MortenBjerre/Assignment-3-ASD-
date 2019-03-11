package game;

public class Board {
	// Class to manage the board
	private static int [] [] board = { 
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{2,0,2,0,2,0,2,0},
			{0,2,0,2,0,2,0,2},
			{2,0,2,0,2,0,2,0}		
			};
	
	public void print() { // function prints out the board

		System.out.print("    0   1   2   3   4   5   6   7   <- x axis \n");
		for(int i = 0; i < this.rows(); i++) {
			System.out.println("  +-------------------------------+ ");
			System.out.print(i+ " | ");
			for(int j = 0; j < this.columns(); j++) {
				if (board[i][j] == 0) {
					if (j == (this.columns() - 1)) {
						System.out.println("  |");
					}
					else {
						System.out.print("    ");
					}
				}
				else {
					if (j == (this.columns() - 1)) {
						System.out.println(board[i][j]+" |");
					}
					else {
						System.out.print(board[i][j]+"   ");
					}
				}
			}
		}
		System.out.println("  +-------------------------------+  \n    0   1   2   3   4   5   6   7   \n");
	}
	
	public int getNumber(int row, int column) {
		return board[column][row];
	}
	
	public void removePiece(int row, int column) {
		board[column][row] = 0;
	}
	
	public void placePiece(int row, int column, int playerNumber) {
		board[column][row] = playerNumber;
	}	
	// if size of the board were to be changed the rules of the game follows
	// can follow using these two methods
	public int rows() { 
		return board.length;
	}
	public int columns() {
		return board[0].length;
	}
}

