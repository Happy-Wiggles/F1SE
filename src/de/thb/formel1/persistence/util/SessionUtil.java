package de.thb.formel1.persistence.util;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionUtil {
	private static final String PERSISTENCE_UNIT_NAME = "F1-DB";
	
	private static EntityManagerFactory sessionFactory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public static EntityManagerFactory getSessionFactory() {
	// Alternatively, you could look up in JNDI here
		return sessionFactory;
	}
	 
	public static void shutdown() {
	//       Close caches and connection pools
		getSessionFactory().close();
	}
}
