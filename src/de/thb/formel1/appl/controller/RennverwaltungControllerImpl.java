/**
 * Data-Access-Object für Rennen und deren Verwaltung
 */

package de.thb.formel1.appl.controller;

import java.util.List;

import de.thb.formel1.appl.impl.Rennplanungsverwaltung;
import de.thb.formel1.appl.transferObjects.RennenTransferObjekt;


/**
 * 
 * Implementiert das Interface RennverwaltungControllerInterface, das nach außen (z. B. GUI) sichtbar ist.
 * Intern wird (bisher nur) Rennplanungsverwaltung aufgerufen.
 * Kann die Aufgabe eines Use Case Controllers (s. Vorlesung später) übernehmen
 * 
 * @author Gabriele Schmidt
 * @version 0.1 
 * @since 09.04.2011
 
 */
public class RennverwaltungControllerImpl implements RennverwaltungControllerInterface{
	
	private Rennplanungsverwaltung rennplanungsverwaltung;
	/**
	 * Default-Konstruktor
	 */
	public RennverwaltungControllerImpl() {
		super();
		rennplanungsverwaltung = new Rennplanungsverwaltung();
	}
	@Override
	public List<RennenTransferObjekt> getAll() {
		// TODO Auto-generated method stub

		return rennplanungsverwaltung.getAll();
	}
	
	@Override
	public List<Integer> getSaisons() {
		return rennplanungsverwaltung.getSaisons();
	}

	@Override
	public List<RennenTransferObjekt> getBySaison(int jahr) {
		// TODO Auto-generated method stub
		return rennplanungsverwaltung.getBySaison(jahr);
	}
	
}
