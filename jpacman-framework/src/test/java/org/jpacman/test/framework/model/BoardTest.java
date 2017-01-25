package org.jpacman.test.framework.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.Tile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BoardTest {
	private final Sprite john = new Sprite() { };
	private final int startx, starty;
	private final Direction dir;
	private final int nextx, nexty;
	private final Board board;
	
	private static final int WIDTH = 10;
	private static final int HEIGHT = 20;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param d
	 * @param nx
	 * @param ny
	 */
	public BoardTest(int x, int y, Direction d, int nx, int ny) {
		startx = x;
		starty = y;
		dir = d;
		nextx = nx;
		nexty = ny;
		board = new Board(WIDTH, HEIGHT);
	}

	
	/** 
	 * The actual test case.
	 */
	@Test(expected=AssertionError.class)
	public void testSpritAtBoard() {
		board.put(john, startx, starty);
		assertEquals(board.spriteAt(startx, starty), john);
		
		board.put(john, WIDTH+1, HEIGHT);
		assertEquals(board.spriteAt(WIDTH, HEIGHT), john);
		board.put(john, -1, -1);
		assertEquals(board.spriteAt(-1, -1), john);
		
	}
	
	@Test
	public void testWithinBoarder() {
		board.put(john, WIDTH-1, HEIGHT-1);
		assertTrue(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile = new Tile(WIDTH+1, HEIGHT+1);
		john.deoccupy();
		john.occupy(tile);
		assertFalse(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile2 = new Tile(WIDTH-1, HEIGHT+1);
		john.deoccupy();
		john.occupy(tile2);
		assertFalse(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile3 = new Tile(WIDTH+1, HEIGHT-1);
		john.deoccupy();
		john.occupy(tile3);
		assertFalse(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile4 = new Tile(0, 0);
		john.deoccupy();
		john.occupy(tile4);
		assertTrue(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile5 = new Tile(-1, -1);
		john.deoccupy();
		john.occupy(tile5);
		assertFalse(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
		Tile tile6 = new Tile(0, -1);
		john.deoccupy();
		john.occupy(tile6);
		assertFalse(board.withinBorders(john.getTile().getX(),john.getTile().getY()));
		
	}

	
	/**
	 * List of (x,y)/Direction/(newx,newy) data points.
	 * @return Test data to be fed to constructor.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] values = new Object[][] {
				// x-axis boundaries, y random inpoints
				// left boundary
				{ 2, 2, Direction.UP, 2, 1 },
				{ 2, 2, Direction.DOWN, 2, 3 },
				{ 2, 2, Direction.LEFT, 1, 2 },
				{ 2, 2, Direction.RIGHT, 3, 2 },
				// worm holes
				{ 0, 2, Direction.LEFT, WIDTH - 1, 2 },
				{ WIDTH - 1, 2, Direction.RIGHT, 0, 2 },
				{ 2, 0, Direction.UP, 2, HEIGHT - 1 },
				{ 2, HEIGHT - 1, Direction.DOWN, 2, 0 } };
		return Arrays.asList(values);
	}

}
