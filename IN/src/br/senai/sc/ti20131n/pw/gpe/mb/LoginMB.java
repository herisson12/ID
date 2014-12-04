package br.senai.sc.ti20131n.pw.gpe.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.senai.sc.ti20131n.pw.gpe.dao.AdministradorDao;
import br.senai.sc.ti20131n.pw.gpe.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Administrador;
import br.senai.sc.ti20131n.pw.gpe.entity.Cliente;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

	private int tipoLogin;
	private Administrador admin;
	private Cliente pessoa;
	private String login;
	private String senha;

	public LoginMB() {
		admin = null;
		tipoLogin = 0;

	}

	public void fazLogin(ActionEvent event) {
		// System.out.println("tentando Login");
		Administrador adm = getAdminDao().findLogin(login, senha);
		Cliente pes = getPesDao().findLogin(login, senha);

		if (adm != null) {
			admin = adm;
			System.out.println("Admin logado " + admin.getUsuario());
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap()
					.put("userLogado", admin);
			tipoLogin = 1; // adm
		} else if (pes != null) {
			pessoa = pes;
			System.out.println("Cliente logado " + pes.getUsuario());
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("userLogado", pes);
			tipoLogin = 2; // Cliente
		} else {
			tipoLogin = 0;
		}
	}

	public String getNomeUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		Administrador admin = (Administrador) context.getExternalContext()
				.getSessionMap().get("userLogado");
		return admin.getNome();
	}

	public void fazLogout(ActionEvent event) {
		admin = null;
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("userLogado", null);
		tipoLogin = 0;
	}

	public Boolean logado() {
		return admin != null ? true : false;
	}

	public Administrador usuarioLogado() {
		return admin;
	}

	public AdministradorDao getAdminDao() {
		return new AdministradorDao(null);
	}

	public ClienteDao getPesDao() {
		return new ClienteDao(null);
	}

	public String getLogin() {
		System.out.println(login);
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(int tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

}
