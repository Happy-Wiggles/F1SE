/**
 * 
 */
package de.thb.formel1.dao;

import java.util.List;

import de.thb.formel1.appl.transferObjects.SaisonTransferObjekt;

/** 
 * Ist ein Interface für die Saisons
 *  Entspricht dem Entwurfsmuster Fassade
 * 
 * @author Susanne Busse
 * @version 0.1 
 * @since 03.04.2019
 * 
 */
public interface SaisonDAOInterface {
	
	/**
	 * gibt eine Liste aller bereits geplanten Saisons zurück, d.h. solche, zu
	 * denen es bereits mindestens ein Rennen gibt.
	 * @return Liste der Saisons als Transferobjekte
	 */
	public List<SaisonTransferObjekt> getSaisons();

}
