package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Contato;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class ContatoDao {

	private EntityManager entityManager;

	public ContatoDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Contato> listar() {
		Query query = entityManager.createQuery("From Contato", Contato.class);
		return query.getResultList();
	}

	public void salvar(Contato Contato) {
		entityManager.merge(Contato);
	}

	public Contato buscarPorId(Long id) {
		return entityManager.find(Contato.class, id);
	}

	public Contato excluir(Long id) {
		Contato Contato = entityManager.find(Contato.class, id);
		entityManager.remove(Contato);
		return Contato;
	}

}