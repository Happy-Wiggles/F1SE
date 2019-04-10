/**
 * Title:      SaisonDAOImpl.java
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
import javax.persistence.Query;

import de.thb.formel1.appl.transferObjects.SaisonTransferObjekt;
import de.thb.formel1.persistence.impl.Saison;

/**
 * Implementiert das Interface SaisonDAOInterface, das nach auﬂen sichtbar
 * ist. Intern werden Eigenschaften von Eclipselink gekapselt.
 */
public class SaisonDAOImpl implements SaisonDAOInterface {

	EntityManager em;
	
	/**
	 * creates a new SaisonDAOImpl object.
	 * @param elDAO Manager zur Benutzung von EclipseLink
	 * @throws PersistenceException Persistenzschicht konnte nicht korrekt initialisiert werden
	 */
	public SaisonDAOImpl(EclipseLinkDAO elDAO) throws PersistenceException {

		super();
		if (elDAO == null)
			throw new PersistenceException("Persistenzschicht nicht initialisiert.");
		this.em = elDAO.getEntityManager();
	}

	
	
	/* (non-Javadoc)
	 * @see de.thb.formel1.dao.SaisonDAOInterface#getSaisons()
	 */
	@Override
	public List<SaisonTransferObjekt> getSaisons() {

		List<SaisonTransferObjekt> saisonTransfer = new ArrayList<SaisonTransferObjekt>();
		List<Saison> saisons;
		SaisonTransferObjekt sto;
		
		Query q = em.createNativeQuery("select s.Jahr "
				+ "from saison s where "
				+ "exists (select * from rennen r "
				+ "where r.Jahr = s.Jahr)", Saison.class);
		saisons = q.getResultList();
		
		for (Saison curSaison : saisons) {
			
			sto = createTransferObject(curSaison);
			saisonTransfer.add(sto);
		}
		return saisonTransfer;
	}

	
	/**
	 * Erzeugt das SaisonTransferObjekt
	 */
	private SaisonTransferObjekt createTransferObject(Saison saison) {
		
		SaisonTransferObjekt sto = new SaisonTransferObjekt();
		sto.jahr = saison.getJahr();

		return sto;
	}
		
}
