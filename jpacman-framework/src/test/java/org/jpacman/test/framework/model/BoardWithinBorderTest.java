package org.jpacman.test.framework.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Tile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test the Board.withinBorder method by means of a series of test cases using the one by one method.
 * 
 * @author Group 2
 */
@RunWith(Parameterized.class)
public class BoardWithinBorderTest {

	private Board board = new Board(WIDTH, HEIGHT);

	private static final int WIDTH = 10;
	private static final int HEIGHT = 20;
	
	private int x;
	private int y;
	private boolean expectedValue;
	
	public BoardWithinBorderTest(Integer x, Integer y, Boolean expectedValue) {
		   this.x = x;
		   this.y = y;
		   this.expectedValue = expectedValue;
	}
	
	@Parameterized.Parameters
	   public static Collection xyValues() {
	      return Arrays.asList(new Object[][] {
	         { 5, 19, true },
	         { 5, 20, false },
	         { 5, 21, false },
	         { 5, -1, false },
	         { 5, 0, true },
	         { 5, 1, true },
	         { 9, 15, true },
	         { 10, 15, false },
	         { 11, 15, false },
	         { -1, 15, false },
	         { 0, 15, true },
	         { 1, 15, true }
	      });
	   }
	
	@Test
    public void testCase() {
		boolean isWithinBorders = board.withinBorders(x, y);
		assertEquals(expectedValue, isWithinBorders);
    }

}
