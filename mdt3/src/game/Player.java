package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
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