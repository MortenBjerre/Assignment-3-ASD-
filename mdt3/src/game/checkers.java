package game;

import java.util.InputMismatchException;
import java.util.Scanner;


class Board {
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

interface Rules {
	public boolean legalMove(Board board, int row, int column, int nrow, int ncolumn, Player p);
	public boolean legalPick(Board board, int row, int column, Player p);
	public boolean moveOnBoard(Board board, int nrow, int ncolumn);
	public boolean correctWay(int column, int ncolumn, Player p);
}

class Game implements Rules {
	
	public boolean legalPick(Board board, int row, int column, Player p) {
		if (moveOnBoard(board, row, column)) {
			return board.getNumber(row, column) == p.getNumber();
		}
		else {
			return false;
		}
	}
	
	public boolean legalMove(Board board, int row, int column, int nrow, int ncolumn, Player p) {
		boolean legal_move = true;
		int diffX = Math.abs(ncolumn - column);
		int diffY = Math.abs(nrow - row);
		
		if (diffX == 2 && diffX == diffY && moveOnBoard(board,nrow,ncolumn)) { // a player tries to jump an opponents piece
		// as i understand you can jump pieces in any direction so we dont check for the way jumped	
			int middlePiece = board.getNumber((nrow - row)/2 + row, (ncolumn - column)/2 + column);
			
			if (middlePiece == p.opponentsPiece()) { // the piece in between should be the opponents
				board.removePiece((nrow - row)/2 + row, (ncolumn - column)/2 + column);
			}
			else { // if it is not the opponents the move is not legal
				legal_move = false;
			}	
			return legal_move;
		}	
		else if (diffX == 1 && diffX == diffY && moveOnBoard(board, nrow, ncolumn) && correctWay(column, ncolumn, p)) {
			return legal_move;
		}
		else {
			return !legal_move;
		}
	}
	
	public boolean moveOnBoard(Board board, int nrow, int ncolumn) {
		return nrow >= 0 && nrow < board.rows() && ncolumn >= 0 && ncolumn < board.columns();
	}	
	public boolean correctWay(int column, int ncolumn, Player p) {
		int diff = ncolumn - column;
		return ((p.getNumber() == 1 && diff > 0) || (p.getNumber() == 2 && diff < 0));
		}
	}


class Player {
	private int playerNumber;
	private int row; private int column; // coordinates of the picked piece
	private int nrow; private int ncolumn; // coordinates of where to move piece
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public int getNumber() {
		return playerNumber;
	}
	public int opponentsPiece() { // used when jumping.
		if (this.playerNumber == 1) { return 2; } 
		else { return 1; }
	}
	
	public void pickPiece(Board board, Game game) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Turn of player: " + playerNumber + "\n");
		board.print();
		while (true) { // runs until a legal pick is made.
			try { // to catch if player decides to input a string etc. prompting an exception
			System.out.println("Enter coordinate of piece to move \n");
			System.out.print("  Enter X: ");
			row = s.nextInt();
			
			System.out.print("  Enter Y: ");
			column = s.nextInt();
			
			if (game.legalPick(board, row, column, this)) {
				break; // if the pick is legal according to the game rules the loop is exited 
			}
			else {
				System.out.println("Please enter position of your own piece.");
			}
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid coordinate.");
				s.next(); // clears the wrong input
				continue;
			} 
		}
	}	
	
	public void movePiece(Board board, Game game) {
		Scanner s = new Scanner(System.in);
		while (true) {
			try {
			System.out.println("Enter coordinate of where to move piece \n");
			System.out.print("  Enter X: ");
			nrow = s.nextInt();
			System.out.print("  Enter Y: ");
			ncolumn = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid coordinate.");
				s.next(); // clears the wrong input
				continue;
			}
			if (game.legalMove(board, row, column, nrow, ncolumn,this)) {
				// the player moves the piece if the move is legal
				board.placePiece(nrow, ncolumn, playerNumber);
				board.removePiece(row, column);
				System.out.println("Piece moved.\n");
				break;
			}
			System.out.println("Please enter a valid coordinate!");
		}
	}
}

public class checkers {
	public static void main(String[] args) {
		// creating games, board and players
		Game game = new Game();
		Board board = new Board();
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		
		System.out.println("   --- GAME ON! ---\nGood luck and have fun\n");
		for(;;) {
			// turn of player 1
			p1.pickPiece(board,game);
			p1.movePiece(board,game);
			// turn of player 2
			p2.pickPiece(board,game);
			p2.movePiece(board,game);
		}
	}
}