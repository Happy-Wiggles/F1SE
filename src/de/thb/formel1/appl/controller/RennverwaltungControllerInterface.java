package de.thb.formel1.appl.controller;

import java.util.List;

import de.thb.formel1.appl.transferObjects.RennenTransferObjekt;

/** Ist ein Interface für die Rennplanungsverwaltung, das nach außen (z. B. GUI) sichtbar ist.
 *  Entspricht dem Entwurfsmuster Fassade
 * 
 * @author Gabriele Schmidt
 * @version 0.1 
 * @since 09.04.2011
 * 
 */

public interface RennverwaltungControllerInterface {
	public List<RennenTransferObjekt> getAll();
	public List<Integer> getSaisons();
	public List<RennenTransferObjekt> getBySaison(int jahr) ;
	
}
