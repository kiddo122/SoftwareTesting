package org.jpacman.test.framework.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.IPacmanInteraction;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanKeyListener;
import org.junit.Before;
import org.junit.Test;

/**
 * Smoke test that just creates the button panel.
 * 
 * @author Group2
 */
public class PacmanKeyListenerTest {

	private PacmanKeyListener pacmanKeyListener;
	
	@Before
	public void setUp() throws FactoryException {
		MainUI mainUI = new MainUI();
		mainUI.initialize();
		pacmanKeyListener = (PacmanKeyListener) mainUI.getKeyListeners()[0];
	}
	
	/**
	 * Smoke test that simulates up key press.
	 */
	@Test
	public void pacmanKeyListenerUPKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	/**
	 * Smoke test that simulates down key press.
	 */
	@Test
	public void pacmanKeyListenerDOWNKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	/**
	 * Smoke test that simulates left key press.
	 */
	@Test
	public void pacmanKeyListenerLEFTKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	

	/**
	 * Smoke test that simulates rigth key press.
	 */
	@Test
	public void pacmanKeyListenerRIGHTKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	/**
	 * Smoke test that simulates Q key press.
	 */
	@Test
	public void pacmanKeyListenerQKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_Q);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	/**
	 * Smoke test that simulates X key press.
	 */
	@Test
	public void pacmanKeyListenerXKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_X);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	/**
	 * Smoke test that simulates S key press.
	 */
	@Test
	public void pacmanKeyListenerSKeySmokeTest() {
		KeyEvent mockedKeyEvent = Mockito.mock(KeyEvent.class);
		Mockito.when(mockedKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_S);
		pacmanKeyListener.keyPressed(mockedKeyEvent);
	}
	
	

}
