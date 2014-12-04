package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.gpe.entity.Anuncio;

public class AnuncioDAOTest extends DBUnitTest {

	public AnuncioDAOTest() {
		super();
	}

	private void gravaAnuncio() {
		Anuncio anuncio = new Anuncio();
		anuncio.setNome("Nome_Teste_01");
		anuncio.setSite("Site_Teste_01");
		anuncio.setDescricao("Descricao_Teste_01");
		anuncio.setLocal("Local_Teste_01");
		anuncio.setTelefone("Telefone_Teste_01");
		anuncio.setCategoria("Categoria_Teste_01");
		anuncioDao.salvar(anuncio);

		Anuncio anuncio2 = new Anuncio();
		anuncio2.setNome("Nome_Teste_02");
		anuncio2.setSite("Site_Teste_02");
		anuncio2.setDescricao("Descricao_Teste_02");
		anuncio2.setLocal("Local_Teste_02");
		anuncio2.setTelefone("Telefone_Teste_02");
		anuncio2.setCategoria("Categoria_Teste_02");
		anuncioDao.salvar(anuncio2);

		Anuncio anuncio3 = new Anuncio();
		anuncio3.setNome("Nome_Teste_03");
		anuncio3.setSite("Site_Teste_03");
		anuncio3.setDescricao("Descricao_Teste_03");
		anuncio3.setLocal("Local_Teste_03");
		anuncio3.setTelefone("Telefone_Teste_03");
		anuncio3.setCategoria("Categoria_Teste_03");
		anuncioDao.salvar(anuncio3);
	}

	@Test
	public void testAnuncioDao() throws SQLException, Exception {
		beginAnuncio();
		gravaAnuncio();
		closeAnuncio();

		// Carregamento do estado atual do banco de dados.
		IDataSet dataBase = getConnection().createDataSet();
		ITable tabelaAtual = dataBase.getTable("anuncio");

		// Carregamento do arquivo de controle (anuncio.xml)
		IDataSet dataBaseXML = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(new File("control/anuncio.xml")));
		ITable tabelaControle = dataBaseXML.getTable("anuncio");

		Assertion.assertEquals(tabelaControle, tabelaAtual);

	}
}
