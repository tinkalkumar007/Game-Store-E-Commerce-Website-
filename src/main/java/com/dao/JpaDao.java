package com.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDao<E> {
	private static EntityManagerFactory factory=Persistence.createEntityManagerFactory("JavaProject");
	
	public JpaDao() {
		super();
	}
	public E create(E entity) {
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}
	
	public E update(E entity) {
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}
	
	public E get(Class<E> type,Object id) {
		EntityManager em=factory.createEntityManager();
		E entity=em.find(type, id);
		if(entity!=null) {
			em.refresh(entity);
		}
		em.close();
		return entity;
	}
	
	public void delete(Class<E> type,Object id) {
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Object entity =em.getReference(type, id);
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
		return;
	}
	public List<E> findWithNamedQuery(String query){
		EntityManager em=factory.createEntityManager();
		Query q=em.createNamedQuery(query);
		List<E> result=q.getResultList();
		
		em.close();
		return result;
	}
	
	public List<E> findWithNamedQuery(String query,String paramName,Object paramValue){
		EntityManager em=factory.createEntityManager();
		Query q=em.createNamedQuery(query);
		q.setParameter(paramName, paramValue);
		List<E> result=q.getResultList();
		
		em.close();
		return result;
	}
	
	public List<E> findWithNamedQuery(String query,int firstRes,int maxRes){
		EntityManager em=factory.createEntityManager();
		Query q=em.createNamedQuery(query);
		q.setFirstResult(firstRes);
		q.setMaxResults(maxRes);
		List<E> result=q.getResultList();
		
		em.close();
		return result;
	}
	public List<Object[]> findWithNamedQueryObjects(String query,int firstRes,int maxRes){
		EntityManager em=factory.createEntityManager();
		Query q=em.createNamedQuery(query);
		q.setFirstResult(firstRes);
		q.setMaxResults(maxRes);
		List<Object[]> result=q.getResultList();
		
		em.close();
		return result;
	}
	public long countWithNamedQuery(String queryName) {
		EntityManager em = factory.createEntityManager();

		Query query= em.createNamedQuery(queryName);
		
		long result = (long) query.getSingleResult();
		
		em.close();
		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName,Map<String,Object> parameters){
		EntityManager em = factory.createEntityManager();
		
		Query query= em.createNamedQuery(queryName);
		Set<Entry<String,Object>> setParam=parameters.entrySet();
		
		for(Entry<String,Object> param:setParam) {
			query.setParameter(param.getKey(),param.getValue());
		}
		return query.getResultList();
	}
	
	public long countWithNamedQuery(String queryName, String paramName, Object paramValue) {
		EntityManager entityManager = factory.createEntityManager();

		Query query= entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		
		long result = (long) query.getSingleResult();
		
		entityManager.close();
		return result;
	}
	
	public void close() {
		if(factory != null) {
			factory.close();
		}
	}
}
