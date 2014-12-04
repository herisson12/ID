package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.CategoriaDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Categoria;

@ManagedBean
public class CategoriaMb {
	private CategoriaDao categoriaDao;
	private List<Categoria> listCategorias;
	private Categoria categoria;

	public List<Categoria> getListCategorias() {
		if (listCategorias == null) {
			listCategorias = categoriaDao.listar();
		}
		return listCategorias;
	}

	@PostConstruct
	public void init() {
		categoriaDao = new CategoriaDao(null);
		categoria = new Categoria();
	}

	public String salvar() {
		categoriaDao.salvar(getCategoria());
		categoria = new Categoria();
		return "";
	}

	public String carregarEdicao(String id) {
		categoria = categoriaDao.buscarPorId(Long.parseLong(id));
		return "categoriaform";
	}

	public String excluir(String id) {
		categoriaDao.excluir(Long.parseLong(id));
		listCategorias = null;
		return "listCategorias";
	}

	// GET SET
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

}
