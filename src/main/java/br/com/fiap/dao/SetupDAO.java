/**
 * Digital Business Enablement
 * FIAP - Faculdade de Informática e Administração Paulista
 * Professor Joao Carlos Lima e Silva
 *
 * @class SetupDAO.java
 * @description: Introducao JSF & Persistência com JPA
 * 				 Persistencia de dados com DAO
 * @author daniloboccomino - RM85473
 * @since Apr 6, 2021
 */

package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.model.Setup;
import br.com.fiap.util.EntityManagerFacade;

public class SetupDAO {
	
	private EntityManager em = new EntityManagerFacade().getEntityManager();

	public void save(Setup setup) {
				
		em.getTransaction().begin();
		em.persist(setup);
		em.getTransaction().commit();
		
		em.clear();
	}

	public List<Setup> getAll() {
		String jpql = "SELECT s FROM Setup s";
		return em.createQuery(jpql, Setup.class).getResultList();
	}

	public Setup findByid(Long id) {
		return em.find(Setup.class, id);
	}

	public void update(Setup setup) {
		em.getTransaction().begin();
		em.merge(setup);
		em.flush();
		em.getTransaction().commit();
	}
	
}
