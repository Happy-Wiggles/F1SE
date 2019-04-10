/**
 * Title:      Formel1DAOFactory.java
 * Copyright:  Copyright (c) 2011
 * Company:    TH Brandenburg, FB Informatik und Medien
 * @author     Busse
 * @version 1.0
 *
 * Created on 08.05.2011
 */
package de.thb.formel1.dao;

/**
 * Die DAOFactory erzeugt DAO, so dass nur die Interfaces der DAOs
 * genutzt werden müssen.
 * 
 * GGf. muss die Factory noch so erweitert werden, dass verschiedene 
 * DAOs erzeugt werden können.
 */
public class Formel1DAOFactory {

	/**
	 * erzeugt ein DAO zu Daten von Rennen
	 * @param dbSessionDAO Objekt, das die aktuelle Session repräsentiert
	 * @return RennenDAOInterface
	 */
	public static RennenDAOInterface buildRennenDAO(EclipseLinkDAO elDAO) 
			throws PersistenceException {
		
        return new RennenDAOImpl(elDAO);
        
    }

	
	/**
	 * erzeugt ein DAO zu Saisons
	 * @param dbSessionDAO Objekt, das die aktuelle Session repräsentiert
	 * @return SaisonDAOInterface
	 */
	public static SaisonDAOInterface buildSaisonDAO(EclipseLinkDAO dbSessionDAO) 
			throws PersistenceException {
		
        return new SaisonDAOImpl(dbSessionDAO);
        
    }

}
