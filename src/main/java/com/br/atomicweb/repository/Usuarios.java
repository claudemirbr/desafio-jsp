package com.br.atomicweb.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.br.atomicweb.model.Usuario;

//https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html
//https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html
//https://gist.github.com/mlecoutre/4088178

public class Usuarios {
	//private static final String PERSISTENCE_UNIT_NAME = "default";
	//private static EntityManagerFactory factory;

	public List<Usuario> getUsuarios() throws ClassNotFoundException {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
	
	public Usuario autenticaUsuario(Usuario usuario) {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		return (Usuario) em.createQuery("select u from Usuario u where login = :login and senha = :senha")
			.setParameter("login", usuario.getLogin())
			.setParameter("senha", usuario.getSenha())
			.getSingleResult();
	}

	public Usuario getUsuario(Usuario usuario) {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		return (Usuario) em.createQuery("select u from Usuario u where codigo = :codigo")
			.setParameter("codigo", usuario.getCodigo())
			.getSingleResult();
	}

	public void salvaUsuario(Usuario usuario) {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public void editaUsuario(Usuario usuario) {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	public void deletaUsuario(Usuario usuario) {
		EntityManager em = CriaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
		em.getTransaction().commit();
		em.close();
	}

}
