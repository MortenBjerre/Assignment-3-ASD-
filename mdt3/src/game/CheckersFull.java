package game;

public class CheckersFull {

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
