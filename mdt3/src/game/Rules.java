package game;

public interface Rules {
	public boolean legalMove(Board board, int row, int column, int nrow, int ncolumn, Player p);
	public boolean legalPick(Board board, int row, int column, Player p);
	public boolean moveOnBoard(Board board, int nrow, int ncolumn);
	public boolean correctWay(int column, int ncolumn, Player p);
}