package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Cliente;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class ClienteDao {

	private EntityManager entityManager;

	public ClienteDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Cliente> listar() {
		Query query = entityManager.createQuery("From Cliente", Cliente.class);
		return query.getResultList();
	}

	public void salvar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return entityManager.find(Cliente.class, id);
	}

	public Cliente excluir(Long id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		entityManager.remove(cliente);
		return cliente;
	}

	public Cliente findLogin(String user, String password) {
		try {
			Query query = entityManager.createQuery(
					"From Cliente where usuario = ? and senha = ?",
					Cliente.class);
			query.setParameter(1, user);
			query.setParameter(2, password);
			return (Cliente) query.getSingleResult();
		} catch (NoResultException nru) {
			return null;
		}
	}
}
