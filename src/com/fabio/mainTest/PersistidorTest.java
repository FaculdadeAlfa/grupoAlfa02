package com.fabio.mainTest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fabio.modelo.Automovel;
import com.fabio.modelo.Autorizacao;
import com.fabio.modelo.Marca;
import com.fabio.modelo.Modelo;
import com.fabio.modelo.Usuario;
import com.fabio.util.JPAUtil;

public class PersistidorTest {
	static EntityManager em;

	public static void main(String[] args) {
		em = JPAUtil.getEntityManager();

		inserirUsuario();
	}
	
	
	static private void listarAutomoveis(){
		Query q = em.createQuery("select a from Automovel a");
		List<Automovel> automoveis = q.getResultList();
//		for (Automovel automovel : automoveis) {
//			System.out.println(automovel.getId() +" - "+automovel.getMarca());
//		}
	}

	static private void listarMarcas(){
		Query q = em.createQuery("select a from Marca a");
		List<Marca> marcas = q.getResultList();
		for (Marca marca : marcas) {
			System.out.println(marca.getId() +" - "+marca.getNome());
			
			for (Modelo modelo : marca.getModelos()) {
				System.out.println(" - "+ modelo.getNome());
			}
		}
	}
	
	static private void excluir(Long id){
		EntityTransaction et = em.getTransaction();
		Automovel automovel = em.getReference(Automovel.class, id);
		
		et.begin();
		em.remove(automovel);
		et.commit();
	}
	
	static private void inserirAutomoveis(){
		Automovel automovel = new Automovel();
		automovel.setObservacoes("semi novo");
		automovel.setAnoFabricacao(2013);
		automovel.setAnoModelo(2014);
		
		
		EntityTransaction transaction = em .getTransaction();
		transaction.begin();
		
		
//		em.persist(modelo);
		
		transaction.commit();
		listarMarcas();
		em.close();
	}
	
	static private void inserirModelos(){
		//Dono do relacionamento
		Marca marca = new Marca();
		marca.setNome("Ford");
		
		Modelo modelo = new Modelo();
		modelo.setNome("Ka");
		
		List<Modelo> modelos = new ArrayList<Modelo>();
		modelos.add(modelo);
		marca.setModelos(modelos);

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
//		marca = em.getReference(Marca.class, 2L);
//		modelo.setMarca(marca);
		
		em.persist(marca);
//		em.persist(modelo);
		
		transaction.commit();
		listarMarcas();
		em.close();
		
	}
	
	static private void inserirUsuario(){
		Usuario usuario = new Usuario();
		usuario.setUsername("fabio.gomes");
		usuario.setPassword("123");
		usuario.setEnable(true);
		
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ROLE_USER");
		
		List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
		autorizacoes.add(autorizacao);
		
		usuario.setAutorizacoes(autorizacoes);
		
		EntityTransaction transaction = em .getTransaction();
		transaction.begin();
		
		
		em.persist(autorizacao);
		em.persist(usuario);
		
		transaction.commit();
		listarMarcas();
		em.close();
		
	}



}
