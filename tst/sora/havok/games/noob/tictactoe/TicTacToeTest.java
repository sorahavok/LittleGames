package sora.havok.games.noob.tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;

public class TicTacToeTest {

	private static final Player X = Player.X;
	private static final Player O = Player.O;

	@Test public void movesLeft() {
		final TicTacToeGame game = new TicTacToeGame(1);
		assertTrue(game.movesLeft());
		game.makeMove(0, 0, X);
		assertTrue(!game.movesLeft());
	}
	
	@Test public void noWinners1by1() {
		emptyBoard(1);
	}

	@Test public void noWinnersEmptyBoard() {
		emptyBoard(3);
	}
	
	@Test public void noWinners_2By2() {
		final Player[][] board = { { X, O }, { null, null } };
		whoWon(board, false, false);
	}

	@Test public void noWinners_3By3() {
		final Player[][] board = { { X, O, X }, { X, O, X }, { O, X, O } };
		whoWon(board, false, false);
	}
	
	@Test public void XWins1by1() {
		final Player[][] board = { { X } };
		whoWon(board, true, false);
	}

	@Test public void XWins_diag() {
		final Player[][] board = { { X, O }, { null, X } };
		whoWon(board, true, false);
	}
	
	@Test public void OWins_antiDiag() {
		final Player[][] board = { { null, O }, { O, X } };
		whoWon(board, false, true);
	}

	@Test public void XWins_col() {
		final Player[][] board = { { X, O, O }, { X, O, X }, { X, null, null } };
		whoWon(board, true, false);
	}

	@Test public void BothWin_row() {
		final Player[][] board = { { O, O, O, O}, { X, X, X, X}, { X, null, null, null }, { O, null, null, null } };
		whoWon(board, true, true);
	}

	private void emptyBoard(int size) {
		whoWon(new Player[size][size], false, false);
	}
	
	private void whoWon(Player[][] board, boolean xWins, boolean oWins) {
		final TicTacToeGame game = new TicTacToeGame(board);
		assertTrue(game.playerWon(X) == xWins);
		assertTrue(game.playerWon(O) == oWins);
	}
}
