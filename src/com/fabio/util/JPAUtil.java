package com.fabio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("persistenceUnit");
	
	public static EntityManager getEntityManager(){
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
	
//	public static Statistics getStatistics(){
//		EntityManager em = JPAUtil.getEntityManager();
//		Session session = (Session) em.getDelegate();
//		SessionFactory factory = session.getSessionFactory();
//		Statistics statistics = factory.getStatistics();
//		
//		return statistics;
//	}

//	public static void printStatistics(){
//		Statistics statistics = getStatistics();
//		System.out.println("Quantidade de entidades buscadas: "+ statistics.getEntityFetchCount());
//		System.out.println("Quantidade de entidades Carregadas: "+ statistics.getEntityLoadCount());
//		System.out.println("Quantidade de listas buscadas: "+ statistics.getCollectionFetchCount());
//	}

}
