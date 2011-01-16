package me.gilday.snakesAndLadders;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class Board {
	
	private static final int MAX_BOARD_SIZE = 128;
	private static final int SNAKE_OR_LADDER_DISTANCE = 0;
	private static final int NO_MOVE_DISTANCE = -1;
	
	private final boolean[][] snakesLadders;
	
	public Board(int size) {
		Preconditions.checkArgument(size >= 0 && size <= MAX_BOARD_SIZE, "Size must be a positive number, no greater than %s", MAX_BOARD_SIZE);
		this.snakesLadders = new boolean[size][size];
		
	}
	
	public void addLadder(int start, int end) {
		Preconditions.checkArgument(start < end, "End of ladder must be after the start");
		addSnakeOrLadder(start, end);
	}
	
	public void addSnake(int start, int end) {
		Preconditions.checkArgument(start > end, "End of snake must be before the start");
		addSnakeOrLadder(start, end);
	}
	
	public int getEndSquare() {
		return this.snakesLadders.length - 1;
	}
	
	public int getDistance(int start, int end) {
		Preconditions.checkArgument(end < this.snakesLadders.length, "End must be within the board, smaller than %s.  Was %s", this.snakesLadders.length, end);
		
		if(snakesLadders[start][end] == true) {
			return SNAKE_OR_LADDER_DISTANCE;
		}
		
		if((end <= start + 6) && (start < getEndSquare())) {
			return end - start;
		}		
		
		return NO_MOVE_DISTANCE;
	}
	
	public List<Integer> getDestinations(int start) {
		
		if(start == getEndSquare()) {
			return Lists.newArrayList();
		}
		
		List<Integer> destinations = Lists.newArrayList();
		
		//add all possible dice throws
		for (int i = 1; i < 7 && (start + i <= getEndSquare()); i++) {
			destinations.add(start + i);
		}
		
		//determine if any ladders or snakes start here
		for(int i = 0; i< this.snakesLadders.length; i++) {
			if(this.snakesLadders[start][i]) {
				destinations.add(i);
			}
		}
		
		return destinations;
	}
	
	public List<Integer> getPredecessors(int start) {
		
		if(start == 0) {
			return Lists.newArrayList();
		}
		
		List<Integer> predescessors = Lists.newArrayList();
		for(int i = 6; i > 0 && start - i >= 0 ; i--) {
			predescessors.add(start - i);
		}
		
		//determine if any snakes or ladders led here
		for(int i = 0; i< this.snakesLadders.length; i++) {
			if(this.snakesLadders[i][start]) {
				predescessors.add(i);
			}
		}
		
		return predescessors;
		
	}
	
	private void addSnakeOrLadder(int start, int end) {
		this.snakesLadders[start][end] = true;
	}	

}
