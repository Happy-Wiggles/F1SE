package de.thb.formel1.appl.impl;

import java.util.ArrayList;
import java.util.List;

import de.thb.formel1.appl.transferObjects.RennenTransferObjekt;
import de.thb.formel1.appl.transferObjects.SaisonTransferObjekt;
import de.thb.formel1.dao.EclipseLinkDAO;
import de.thb.formel1.dao.Formel1DAOFactory;
import de.thb.formel1.dao.RennenDAOInterface;
import de.thb.formel1.dao.PersistenceException;
import de.thb.formel1.dao.SaisonDAOInterface;

/**
 * Rennplanungsverwaltung ist der eigentliche Controller in der Anwendungsschicht.
 * Hier wird mehr Funktionalität entstehen als in dem Fassadencontroller, der einen Teil davon verbergen soll.
 * 
 * @author Susanne Busse
 * @version 0.1 
 * @since 03.04.2019
 * 
 */
public class Rennplanungsverwaltung {
	
	// Das Sessionobjekt koennte die Transaktionssteuerung ermoeglichen (bisher nicht benutzt).
	private EclipseLinkDAO dbSession;
	
	private RennenDAOInterface rennenDao;
	private SaisonDAOInterface saisonDao;
	private List<RennenTransferObjekt> listRto;
	private List<SaisonTransferObjekt> listSto;
	
	
	public Rennplanungsverwaltung() {
		
		dbSession = new EclipseLinkDAO();
		try {
			rennenDao = Formel1DAOFactory.buildRennenDAO(dbSession);
			saisonDao = Formel1DAOFactory.buildSaisonDAO(dbSession);
		} catch (PersistenceException e) {
			// sollte nicht mehr auftreten, wenn new EclipseLinkDAO() geklappt hat.
			e.printStackTrace();
		}

	}
	
	
	public List<RennenTransferObjekt> getAll() {
		// TODO Auto-generated method stub
		if (rennenDao != null)
			listRto = rennenDao.getAll();
		return listRto;
	}
	
	
	public List<Integer> getSaisons() {
		
		List<Integer> s = new ArrayList<Integer>();

		if (saisonDao != null) {
			listSto = saisonDao.getSaisons();
			for (SaisonTransferObjekt sto : listSto)
				s.add(sto.jahr);
		}
		return s;
	}

	
	public List<RennenTransferObjekt> getBySaison(int jahr) {

		if (rennenDao != null)
			return rennenDao.getBySaison(jahr);
		else
			return null;
		
	}

}
