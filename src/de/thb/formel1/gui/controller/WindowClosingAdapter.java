package de.thb.formel1.gui.controller;

import java.awt.event.*;

/**
 * Wird in Swing nicht mehr unbedingt gebraucht. Ist eine allgemein einsetzbare
 * Methode, um Fenster zu schlieﬂen.
 * 
 * @author Gabriele Schmidt
 * @version 0.1 
 * @since 02.05.2010
 * 
 */
public class WindowClosingAdapter extends WindowAdapter {
	private boolean exitSystem;

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Ist
	 * exitSystem true, wird das komplette Programm beendet.
	 */
	public WindowClosingAdapter(boolean exitSystem) {
		this.exitSystem = exitSystem;
	}

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Das
	 * Programm wird nicht beendet.
	 */
	public WindowClosingAdapter() {
		this(false);
	}

	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		if (exitSystem) {
			System.exit(0);
		}
	}
}
