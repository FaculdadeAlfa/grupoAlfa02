package com.fabio.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fabio.modelo.Marca;
import com.fabio.util.JPAUtil;

@ManagedBean
public class MarcaBean {
	@SuppressWarnings("unused")
	private List<Marca> marcas;

	@SuppressWarnings("unchecked")
	public List<Marca> getMarcas() {
		EntityManager em = JPAUtil.getEntityManager();
		Query q = em.createQuery("select m from Marca m");
		return marcas = q.getResultList();
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
	
}
