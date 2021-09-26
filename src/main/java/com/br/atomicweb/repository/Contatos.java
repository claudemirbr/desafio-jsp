package com.br.atomicweb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.br.atomicweb.model.Contato;

//https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html
//https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html

public class Contatos {
	private static final String PERSISTENCE_UNIT_NAME = "default";
	private static EntityManagerFactory factory;

	public Contatos() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public List<Contato> getContatos() throws ClassNotFoundException {
		EntityManager em = factory.createEntityManager();
		return em.createQuery("select c from Contato c", Contato.class).getResultList();
	}

	public void salvaContato(Contato contato) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(contato);
		em.getTransaction().commit();
		em.close();
	}

	public void deletaContato(Contato contato) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(contato);
		em.getTransaction().commit();
		em.close();
	}

}
