package com.br.atomicweb.repository;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.br.atomicweb.model.Contato;

//https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html

@SuppressWarnings("unchecked")
public class Contatos {
	private static final String PERSISTENCE_UNIT_NAME = "default";
	private static EntityManagerFactory factory;

	public Contatos() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public List<Contato> getContatos() {
		EntityManager em = factory.createEntityManager();
		Query q = (Query) em.createQuery("select * from Contato");
		List<Contato> lista = ((javax.persistence.Query) q).getResultList();
		em.close();
		return lista;
	}

	public void salvaContato(Contato contato) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(contato);
		em.getTransaction().commit();
		em.close();
	}

	public void deletaContato(Contato contato) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(contato);
		em.getTransaction().commit();
		em.close();
	}

}
