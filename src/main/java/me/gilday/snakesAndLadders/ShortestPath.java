package me.gilday.snakesAndLadders;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ShortestPath {
	
	public static void main(String[] args) {
		
		/*
		 * s = the source node
		 * d = best estimate of the shortest distance from the source to each node
		 * pi = stores the predecessor of each node on the shortest path from the source
		 * S = the settled nodes, the nodes whose shortest distance from the path has been found
		 * Q = the set of unsettled nodes
		 * 
		 * 
		 * d = infinity
		 * S = empty set
		 * Q = empty set
		 * 
		 * add s to Q
		 * d(s) = 0
		 * 
		 * while Q is not empty
		 * {
		 *     u = extract-minimum(Q)
		 *     add u to S
		 *     relax-neighbours(u)
		 * }
		 * 
		 * 
		 * relax-neighbours(u)
		 * {
		 *     for each node v adjacent to u, v not in S
		 *     {
		 *         if d(v) > d(u) + [u,v]  // a shorter distance exists
		 *         {
		 *             d(v) = d(u) + [u,v]
		 *             pi(v) = u
		 *             add v to Q
		 *         }
		 *     }
		 * }
		 * 
		 * extract-minimum(Q)
		 * {
		 *     find the smallest (as defined by d) vertex in Q
		 *     remove it from Q and return it
		 * } 
		 * 
		 */
		
	}
	
	public static final int INFINITE_DISTANCE = Integer.MAX_VALUE;
	
	private final Board board;
	
	private final PriorityQueue<Integer> unsettledNodes = new PriorityQueue<Integer>(); 
	private final Set<Integer> settledNodes = new HashSet<Integer>();
	private final Map<Integer, Integer> shortestDistances = Maps.newHashMap();
	private final Map<Integer, Integer> predecessors = Maps.newHashMap();
	
	public ShortestPath(Board board) {
		this.board = board;
	}
	
	private void init(int start) {
		settledNodes.clear();
		unsettledNodes.clear();
		shortestDistances.clear();
		predecessors.clear();
		
		setShortestDistance(start, 0);
		unsettledNodes.add(start);
	}

	public List<Integer> execute() {
		init(0);
		
		//current node
		Integer u;
		
		while((u = unsettledNodes.poll()) != null) {
			if (u == this.board.getEndSquare()) break;
			
			settledNodes.add(u);
			
			relaxNeighbours(u);
		}
		
		List<Integer> path = Lists.newArrayList();
		
		Integer node = board.getEndSquare();
		path.add(board.getEndSquare());
		while(node != 0) {
			
			Integer pre = predecessors.get(node);
			path.add(pre);
			node = pre;			
		}
		
		Collections.reverse(path);
		return path;
	}
	
	private void relaxNeighbours(Integer u) {
		for (Integer v : this.board.getDestinations(u)) {
			if (isSettled(v)) continue;
			
			int shortDistance = getShortestDistance(u) + board.getDistance(u, v);
			
			if(shortDistance < getShortestDistance(v)) {
				setShortestDistance(v, shortDistance);
				setPredecessor(v, u);
			}
		}
	}
	
	private boolean isSettled(Integer v) {
		return settledNodes.contains(v);
	}
	
	private int getShortestDistance(Integer v) {
		Integer d = shortestDistances.get(v);
		return (d == null) ? INFINITE_DISTANCE : d;
	}
	
	private void setShortestDistance(Integer v, int distance) {
		unsettledNodes.remove(v);
		shortestDistances.put(v, distance);
		
		unsettledNodes.add(v);
	}
	
	private void setPredecessor(Integer a, Integer b) {
		predecessors.put(a, b);
	}
}
