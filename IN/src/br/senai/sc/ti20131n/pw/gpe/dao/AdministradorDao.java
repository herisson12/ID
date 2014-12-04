package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Administrador;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class AdministradorDao {

	private EntityManager entityManager;

	public AdministradorDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Administrador> listar() {
		Query query = entityManager.createQuery("From Administrador",
				Administrador.class);
		return query.getResultList();
	}

	public void salvar(Administrador administrador) {
		entityManager.merge(administrador);
	}

	public Administrador buscarPorId(Long id) {
		return entityManager.find(Administrador.class, id);
	}

	public Administrador excluir(Long id) {
		Administrador administrador = entityManager.find(Administrador.class,
				id);
		entityManager.remove(administrador);
		return administrador;
	}

	public Administrador findLogin(String user, String password) {
		try {
			Query query = entityManager.createQuery(
					"From Administrador where usuario = ? and senha = ?",
					Administrador.class);
			query.setParameter(1, user);
			query.setParameter(2, password);
			return (Administrador) query.getSingleResult();
		} catch (NoResultException nru) {
			return null;
		}
	}

}