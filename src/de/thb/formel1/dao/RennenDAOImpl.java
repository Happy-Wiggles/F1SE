/**
 * Title:      RennenDAOImpl.java
 * Copyright:  Copyright (c) 2012
 * Company:    TH Brandenburg, FB Informatik und Medien
 * @author     Busse
 * @version 1.0
 *
 * Created on 03.04.2019
 */
package de.thb.formel1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import de.thb.formel1.appl.transferObjects.RennenTransferObjekt;
import de.thb.formel1.persistence.impl.Rennen;
import de.thb.formel1.persistence.impl.Saison;

/**
 * Implementiert das Interface RennenDAOInterface, das nach auﬂen sichtbar
 * ist. Intern werden Eigenschaften von EclipseLink gekapselt.
 */
public class RennenDAOImpl implements RennenDAOInterface {

	EntityManager em;
	
	/**
	 * creates a new RennenDAOImpl object.
	 * @param elDAO Manager zur Benutzung von EclipseLink
	 * @throws PersistenceException Persistenzschicht konnte nicht korrekt initialisiert werden
	 */
	public RennenDAOImpl(EclipseLinkDAO elDAO) throws PersistenceException {
		super();
		if (elDAO == null)
			throw new PersistenceException("Persistenzschicht nicht initialisiert.");
		this.em = elDAO.getEntityManager();
	}

	
	/* (non-Javadoc)
	 * @see de.thb.formel1.dao.RennenDAOInterface#getAll()
	 */
	@Override
	public List<RennenTransferObjekt> getAll() {

		List<RennenTransferObjekt> rtoList = new ArrayList<RennenTransferObjekt>();
		List<Rennen> rennen;
		RennenTransferObjekt rto;
		
		rennen = em.createNamedQuery("Rennen.findAll").getResultList();

		for (Rennen curRennen : rennen) {
			
			rto = createTransferObject(curRennen);
			rtoList.add(rto);
		}
		return rtoList;

	}

	
	
	/* (non-Javadoc)
	 * @see de.thb.formel1.dao.RennenDAOInterface#getByWarengruppe(java.lang.String)
	 */
	@Override
	public List<RennenTransferObjekt> getBySaison(int jahr) {
		
		List<RennenTransferObjekt> rtoList = new ArrayList<RennenTransferObjekt>();
		RennenTransferObjekt newRTO;
		
		List<Saison> saison = em.createQuery("select s from Saison s where s.jahr = '" + jahr + "'").getResultList();
		for (Saison curSaison : saison) {
			
			for (Rennen curRennen : curSaison.getRennens()) {
				
				newRTO = createTransferObject(curRennen);
				rtoList.add(newRTO);					
					
			}
		}

		return rtoList;
	}

	
	/**
	 * Erzeugt das RennenTransferObjekt
	 */
	private RennenTransferObjekt createTransferObject(Rennen rennen) {
		
		return new RennenTransferObjekt(rennen.getDatum(),
									    rennen.getRennstrecke().getName(), 
										rennen.getRennstrecke().getLand().getName());

	}

}
