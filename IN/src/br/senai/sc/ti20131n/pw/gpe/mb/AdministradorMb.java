package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.AdministradorDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Administrador;

@ManagedBean
public class AdministradorMb {
	private AdministradorDao AdministradorDao;
	private List<Administrador> listAdministradores;
	private Administrador Administrador;

	public List<Administrador> getListAdministradors() {
		if (listAdministradores == null) {
			listAdministradores = AdministradorDao.listar();
		}
		return listAdministradores;
	}

	@PostConstruct
	public void init() {
		AdministradorDao = new AdministradorDao(null);
		Administrador = new Administrador();
	}

	public String salvar() {
		AdministradorDao.salvar(getAdministrador());
		Administrador = new Administrador();
		return "listAdministradors";
	}

	public String carregarEdicao(String id) {
		Administrador = AdministradorDao.buscarPorId(Long.parseLong(id));
		return "administradorform";
	}

	public String excluir(String id) {
		AdministradorDao.excluir(Long.parseLong(id));
		listAdministradores = null;
		return "listAdministradores";
	}

	// GET SET
	public Administrador getAdministrador() {
		return Administrador;
	}

	public void setAdministrador(Administrador Administrador) {
		this.Administrador = Administrador;
	}

	public AdministradorDao getAdministradorDao() {
		return AdministradorDao;
	}

	public void setAdministradorDao(AdministradorDao AdministradorDao) {
		this.AdministradorDao = AdministradorDao;
	}

	public void setListAdministradors(List<Administrador> listAdministradors) {
		this.listAdministradores = listAdministradors;
	}

}