package me.gilday.snakesAndLadders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import me.gilday.snakesAndLadders.Board;

import org.junit.Test;

public class BoardUnitTest {
	
	@Test
	public void mustOnlyHaveEdgesToNextBoardSquareOnDefaultBoard() {
		
		Board board = new Board(4);
		
		assertThat(board.getDistance(0, 1), is(1));
		assertThat(board.getDistance(1, 2), is(1));
		assertThat(board.getDistance(2, 3), is(1));
				
		assertThat(board.getDistance(0, 2), is(-1));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void onlyA() {
		Board board = new Board(4);
		board.getDistance(3, 4);
	}
	
	@Test
	public void mustDetermineThatMoveUsesSnake() {
		Board board = new Board(8);
		board.addSnake(7, 3);
		
		assertThat(board.getDistance(7, 3), is(0));
		assertThat(board.getDistance(7, 4), is(-1));
		
	}
	
	@Test
	public void mustDetermineThatMoveUsesLadder() {
		Board board = new Board(8);
		board.addLadder(2, 5);
		
		assertThat(board.getDistance(2, 5), is(0));
		assertThat(board.getDistance(2, 4), is(-1));
		
	}

}
