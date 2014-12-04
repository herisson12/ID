package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Categoria;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class CategoriaDao {

	private EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Categoria> listar() {
		Query query = entityManager.createQuery("From Categoria",
				Categoria.class);
		return query.getResultList();
	}

	public void salvar(Categoria categoria) {
		entityManager.merge(categoria);
	}

	public Categoria buscarPorId(Long id) {
		return entityManager.find(Categoria.class, id);
	}

	public Categoria excluir(Long id) {
		Categoria categoria = entityManager.find(Categoria.class, id);
		entityManager.remove(categoria);
		return categoria;
	}

}
