/**
 * Digital Business Enablement
 * FIAP - Faculdade de Informática e Administração Paulista
 * Professor Joao Carlos Lima e Silva
 *
 * @class UsuarioDao.java
 * @description: persistencia de dados com dao
 * @author daniloboccomino - RM85473
 * @since Apr 17, 2021
 */

package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Usuario;
import br.com.fiap.util.EntityManagerFacade;

public class UsuarioDao {
	
	private EntityManager em = new EntityManagerFacade().getEntityManager();

	public void save(Usuario usuario) {
				
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.clear();
	}

	public List<Usuario> getAll() {
		String jpql = "SELECT u FROM Usuario u";
		return em.createQuery(jpql, Usuario.class).getResultList();
	}
	
	public Usuario findByid(Long id) {
		return em.find(Usuario.class, id);
	}

	public void update(Usuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.flush();
		em.getTransaction().commit();
	}

	public void delete(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
	}

	public boolean exist(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE email = :email AND password = :password", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("password", usuario.getPassword());
		
		Usuario result;
		try {
			result = query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			e.printStackTrace();
			return false;
		}

	}

}
