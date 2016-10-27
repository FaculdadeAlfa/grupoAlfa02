package com.fabio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fabio.util.JPAUtil;

public abstract class GenericDao<E, I extends Serializable> {
	
	protected Class<E> entityClass;
	
	
	public GenericDao(){
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		 this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> listar(Class clazz){
		Query query = JPAUtil.getEntityManager().createQuery("select a from "+clazz.getName());
		return query.getResultList();
	}

	public void salvar(E entidade){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
		em.close();
	}

	public void excluir(E entidade){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		entidade = em.merge(entidade);
		em.remove(entidade);
		em.getTransaction().commit();
		em.close();
	}
	
	public E buscar(Long id){
		EntityManager em = JPAUtil.getEntityManager();
		return em.find(entityClass, id);
	}

	public E alterar(E entidade){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		E entidadeRetorno = em.merge(entidade);
		em.getTransaction().commit();
		em.close();
		return entidadeRetorno;
	}
}
