package org.jpacman.test.framework.factory;

import static org.junit.Assert.assertEquals;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the constructors of the FactoryException class
 * 
 * @author Group2
 */
public class FactoryExceptionTest {


	private MapParser parser;

	private final String[] map = new String[] { 
			"#####", 
			"#...#", 
			"#badmap#", 
			"#   #",
			"#####" 
	};

	
	/**
	 * Create the standard factory and parser.
	 */
	@Before
	public void setUp() {
		IGameFactory factory = new DefaultGameFactory();
		parser = new MapParser(factory);
	}

	/**
	 * Test parsing of an invalid map.
	 * 
	 * @throws FactoryException This is expected to be thrown.
	 */
	@Test(expected=FactoryException.class)
	public void testFullMap() throws FactoryException {
		Game g = parser.parseMap(map);
	}
}
