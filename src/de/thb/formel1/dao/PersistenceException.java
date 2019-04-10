/**
 * Title:      PersistenceException.java
 * Copyright:  Copyright (c) 2017
 * Company:    TH Brandenburg, FB Informatik und Medien
 * @author     Busse
 * @version 2.0
 *
 * Created on 22.05.2017
 */
package de.thb.formel1.dao;

/**
 * Dient der Kapselung von Fehlern, die beim Zugriff auf die DB 
 * aufgetreten sind. 
 */
public class PersistenceException extends Exception {

	/**
	 * creates a new PersistenceException object.
	 */
	public PersistenceException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * creates a new PersistenceException object.
	 * @param arg0
	 */
	public PersistenceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * creates a new PersistenceException object.
	 * @param arg0
	 */
	public PersistenceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * creates a new PersistenceException object.
	 * @param arg0
	 * @param arg1
	 */
	public PersistenceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
