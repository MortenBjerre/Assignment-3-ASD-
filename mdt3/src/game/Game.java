package game;


public class Game implements Rules {
	
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
