package com.fabio.dao;

import javax.persistence.EntityManager;

import com.fabio.modelo.Automovel;
import com.fabio.modelo.Modelo;
import com.fabio.persistence.QueryDataModel;
import com.fabio.util.JPAUtil;

public class AutomovelDao extends GenericDao<Automovel, Long> {
	
	public QueryDataModel<Automovel> listarDataModel(){
		String jpql = "select a from Automovel a";
		String count = "select count(a.id) from Automovel a";
		return new QueryDataModel<Automovel>(jpql, count);
	}
	
	@Override
	public void salvar(Automovel automovel){
		EntityManager em = JPAUtil.getEntityManager();
		setModeloMerged(automovel, em);
		em.persist(automovel);
		em.getTransaction().commit();
	}

	@Override
	public Automovel alterar(Automovel automovel){
		EntityManager em = JPAUtil.getEntityManager();
		setModeloMerged(automovel, em);
		Automovel entidadeRetorno = em.merge(automovel);
		em.getTransaction().commit();
		return entidadeRetorno;
	}

	private void setModeloMerged(Automovel automovel, EntityManager em) {
		em.getTransaction().begin();
		Modelo modelo = new Modelo();
		if(automovel.getModelo() != null){
			modelo = em.find(Modelo.class, automovel.getModelo().getId());
			automovel.setModelo(modelo);
		}
	}
	
}
