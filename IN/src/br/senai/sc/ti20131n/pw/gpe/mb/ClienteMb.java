package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Cliente;

@ManagedBean
public class ClienteMb {
	private ClienteDao clienteDao;
	private List<Cliente> listclientes;
	private Cliente cliente;

	public List<Cliente> getListClientes() {
		if (listclientes == null) {
			listclientes = clienteDao.listar();
		}
		return listclientes;
	}

	@PostConstruct
	public void init() {
		clienteDao = new ClienteDao(null);
		cliente = new Cliente();
	}

	public String salvar() {
		clienteDao.salvar(getCliente());
		cliente = new Cliente();
		return "";
	}

	public String carregarEdicao(String id) {
		cliente = clienteDao.buscarPorId(Long.parseLong(id));
		return "clienteform";
	}

	public String excluir(String id) {
		clienteDao.excluir(Long.parseLong(id));
		listclientes = null;
		return "listclientes";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listclientes = listClientes;
	}

}
