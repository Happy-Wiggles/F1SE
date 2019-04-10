/**
 * Implementiert EclipseLink und stellt bestimmte Grundfunktionen 
 * zur Datenbank-Manipulation bereit
 * 
 * zur leichteren Austauschbarkeit der JPA-Implementation m¸sste diese
 * Klasse erweitert werden. Da dies hier noch nicht geschehen ist, wurde
 * EclipseLink auch im Namen belassen.
 */

package de.thb.formel1.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import de.thb.formel1.persistence.util.SessionUtil;

public class EclipseLinkDAO {
	private EntityManagerFactory sessionFactory;
	private EntityManager entityManager;
	
	/**
	 * Default-Konstruktor
	 */
	public EclipseLinkDAO() {
		
		sessionFactory =  SessionUtil.getSessionFactory();
		entityManager = sessionFactory.createEntityManager();
	}
	
	/**
	 * Aktueller EntityManager zurueckgeben
	 * @return EntityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Neue Transaktion starten
	 */
	public void startTransaction() {
		EntityTransaction tx = null;
		tx = entityManager.getTransaction();
		tx.begin();
	}
	
	/**
	 * Transaktion erfolgreich abschlieﬂen
	 */
	public void commitTransaction() {
		try{
			getEntityManager().getTransaction().commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Transaktion rueckgaengig machen
	 */
	public void rollbackTransaction() {
		getEntityManager().getTransaction().rollback();
	}
	
	
	/**
	 * schlieﬂt die DB-Session und gibt alle Ressourcen der Session
	 * (= des Entity Managers) frei.
	 */
	public void shutdownSession() {
		
		entityManager.close();
		
	}
	
	
	/**
	 * schlieﬂt die Verbindung zur Datenbank ab
	 */
	public void shutdownDataAccess() {
		
		//	Shutting down the application with the entity manager factory
		SessionUtil.shutdown();
		
	}
	
}
