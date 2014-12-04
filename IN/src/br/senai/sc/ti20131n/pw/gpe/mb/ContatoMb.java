package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.ContatoDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Contato;

@ManagedBean
public class ContatoMb {
	private ContatoDao contatoDao;
	private List<Contato> listcontatos;
	private Contato contato;

	public List<Contato> getListContatos() {
		if (listcontatos == null) {
			listcontatos = contatoDao.listar();
		}
		return listcontatos;
	}

	@PostConstruct
	public void init() {
		contatoDao = new ContatoDao(null);
		contato = new Contato();
	}

	public String salvar() {
		contatoDao.salvar(getContato());
		contato = new Contato();
		return "";
	}

	public String carregarEdicao(String id) {
		contato = contatoDao.buscarPorId(Long.parseLong(id));
		return "contatoform";
	}

	public String excluir(String id) {
		contatoDao.excluir(Long.parseLong(id));
		listcontatos = null;
		return "listContatos";
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ContatoDao getContatoDao() {
		return contatoDao;
	}

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}

	public void setListContatos(List<Contato> listContatos) {
		this.listcontatos = listContatos;
	}

}
