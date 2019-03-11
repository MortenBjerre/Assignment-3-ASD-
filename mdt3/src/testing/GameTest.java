package testing;

import static org.junit.Assert.*;

import org.junit.Test;
import game.Board;
import game.Player;
import game.Game;

public class GameTest {
	
	Player p1 = new Player(1);
	Player p2 = new Player(2);
	Game g = new Game();
	Board board = new Board();
	
	@Test
	public void testLegalPick() {
		assertTrue("Should be a legal pick for player 1", g.legalPick(board, 2, 1, p1));
		assertFalse("Should be an illegal pick for player 2", g.legalPick(board, 2, 1, p2));
		assertTrue("Should be a legal pick for player 2", g.legalPick(board, 5, 6, p2));
	}

	@Test
	public void testLegalMove() {
		assertTrue(g.legalMove(board, 2, 1, 3, 2, p1)); 
		// legal to move forward by one diagonally
		assertFalse(g.legalMove(board, 2, 1, 4, 3, p1));
		// illegal to move forward by two diagonally unless there is a piece in between
		board.placePiece(3, 2, p2.getNumber());
		assertTrue(g.legalMove(board, 2, 1, 4, 3, p1));
	}

	@Test
	public void testMoveOnBoard() {
		assertFalse(g.moveOnBoard(board, 10, 10));
		assertFalse(g.moveOnBoard(board, -10, -10));
	}

	@Test
	public void testCorrectWay() {
		assertTrue(g.correctWay(1, 2, p1));
		// this means going from row 1 to 2 which is legal for player 1
		assertFalse(g.correctWay(1, 2, p2));
		// that is not legal for player 2
	}

}
