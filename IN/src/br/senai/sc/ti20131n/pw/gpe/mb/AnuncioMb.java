package br.senai.sc.ti20131n.pw.gpe.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import br.senai.sc.ti20131n.pw.gpe.dao.AnuncioDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Anuncio;
import br.senai.sc.ti20131n.pw.gpe.entity.Categoria;
import br.senai.sc.ti20131n.pw.gpe.util.UploadImageUtil;

@ManagedBean
public class AnuncioMb {

	private AnuncioDao anuncioDao;
	private List<Anuncio> listAnuncio;
	private Anuncio anuncio;
	private Part logo;

	public List<Anuncio> getListAnuncio() {
		if (listAnuncio == null) {
			listAnuncio = anuncioDao.listar();
		}
		return listAnuncio;
	}

	public List<Anuncio> listarAnuncios(Categoria categoria) {
		listAnuncio = anuncioDao.listarPorCategoria(categoria.getNome());
		return listAnuncio;
	}

	public void setListAnuncio(List<Anuncio> listAnuncio) {
		this.listAnuncio = listAnuncio;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public Part getLogo() {
		return logo;
	}

	public void setLogo(Part logo) {
		this.logo = logo;
	}

	@PostConstruct
	public void init() {
		anuncioDao = new AnuncioDao(null);
		anuncio = new Anuncio();
	}

	// *************** ações *****************************

	public String caminho(String nomeImagem) {
		return UploadImageUtil.getCaminho(nomeImagem);
	}

	public String salvar() throws IOException {
		String nomeImagem = UploadImageUtil.copiar(logo, anuncio.getLogo());
		anuncio.setLogo(nomeImagem);
		anuncioDao.salvar(getAnuncio());
		anuncio = new Anuncio();
		return "";
	}

	public String carregarEdicao(String id) {
		anuncio = anuncioDao.buscarPorId(Long.parseLong(id));
		return "anuncioform";
	}

	public String carregarAnuncio(String id) {
		anuncio = anuncioDao.buscarPorId(Long.parseLong(id));
		return "carregarDivulgacao";
	}

	public String excluir(String id) {
		Anuncio anuncioRemovido = anuncioDao.excluir(Long.parseLong(id));
		UploadImageUtil.deletar(anuncioRemovido.getLogo());
		listAnuncio = null;
		return "listAnuncio";
	}
}
