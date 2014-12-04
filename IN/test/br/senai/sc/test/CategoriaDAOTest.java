package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.gpe.entity.Categoria;

public class CategoriaDAOTest extends DBUnitTest {

	public CategoriaDAOTest() {
		super();
	}

	private void gravaCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria_Teste_01");
		categoriaDao.salvar(categoria);

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Categoria_Teste_02");
		categoriaDao.salvar(categoria2);

		Categoria categoria3 = new Categoria();
		categoria3.setNome("Categoria_Teste_03");
		categoriaDao.salvar(categoria3);
	}

	@Test
	public void testCategoriaDao() throws SQLException, Exception {
		beginCategoria();
		gravaCategoria();
		closeCategoria();

		// Carregamento do estado atual do banco de dados.
		IDataSet dataBase = getConnection().createDataSet();
		ITable tabelaAtual = dataBase.getTable("categoria");

		// Carregamento do arquivo de controle (Categoria.xml)
		IDataSet dataBaseXML = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(new File("control/categoria.xml")));
		ITable tabelaControle = dataBaseXML.getTable("categoria");

		Assertion.assertEquals(tabelaControle, tabelaAtual);

	}
}
