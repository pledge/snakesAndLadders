package me.gilday.snakesAndLadders;

import java.util.List;

import me.gilday.snakesAndLadders.Board;
import me.gilday.snakesAndLadders.ShortestPath;

import org.junit.Test;

public class ShortestPathUnitTest {

	@Test
	public void mustComputeShortestPathToEachNode() {
		
		Board board = new Board(8);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustComputeShortestPathToEachNode: " + path);
		
	}
	
	@Test
	public void mustComputeShortestPathToEachNodeWithLargeBoard() {
		
		Board board = new Board(100);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustComputeShortestPathToEachNode: " + path);
		
	}
	
	
	@Test
	public void mustComputeShortestPathWithLadder() {
		
		Board board = new Board(8);
		board.addLadder(2, 5);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustComputeShortestPathWithLadder: " + path);
		
	}
	
	@Test
	public void mustTakeMultipleLadders() {
		
		Board board = new Board(16);
		board.addLadder(1, 5);
		board.addLadder(6, 14);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustTakeMultipleLadders: " + path);
		
	}
	
	@Test
	public void mustTakeLadderToEndSquare() {
		
		Board board = new Board(16);
		board.addLadder(1, 5);
		board.addLadder(6, 15);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustTakeLadderToEndSquare: " + path);
		
	}
	
	@Test
	public void mustTakeSnakeIfLeadsToBetterLadderQuicker() {
		
		Board board = new Board(100);
		
		board.addLadder(3, 60);
		board.addSnake(64, 55);
		board.addLadder(59, 97);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("mustTakeSnakeIfLeadsToBetterLadderQuicker: " + path);
		
	}
	
	@Test
	public void realBoard() {
		Board board = new Board(100);
		
		board.addLadder( 6, 24);
		board.addLadder(13, 34);
		board.addLadder(20, 22);
		board.addLadder(29, 50);
		board.addLadder(43, 56);
		board.addLadder(47, 90);
		board.addLadder(60, 97);
		board.addLadder(64, 82);
		board.addLadder(73, 94);
		
		board.addSnake(40, 3);
		board.addSnake(46, 5);
		board.addSnake(68, 10);
		board.addSnake(80, 55);
		board.addSnake(89, 54);
		board.addSnake(95, 23);
		board.addSnake(98, 44);
		
		ShortestPath shortestPath = new ShortestPath(board);
		List<Integer> path = shortestPath.execute();
		
		System.out.println("realBoard: " + path);
	}
	
}
