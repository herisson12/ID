package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.gpe.entity.Contato;

public class ContatoDAOTest extends DBUnitTest {

	public ContatoDAOTest() {
		super();
	}

	private void gravaContato() {
		Contato contato1 = new Contato();
		contato1.setNome("Contato_Teste_01");
		contato1.setEmail("Email_Teste_01");
		contato1.setAssunto("Assunto_Teste_01");
		contato1.setMensagem("Mensagem_Teste_01");
		contatoDao.salvar(contato1);

		Contato contato2 = new Contato();
		contato2.setNome("Contato_Teste_02");
		contato2.setEmail("Email_Teste_02");
		contato2.setAssunto("Assunto_Teste_02");
		contato2.setMensagem("Mensagem_Teste_02");
		contatoDao.salvar(contato2);

		Contato contato3 = new Contato();
		contato3.setNome("Contato_Teste_03");
		contato3.setEmail("Email_Teste_03");
		contato3.setAssunto("Assunto_Teste_03");
		contato3.setMensagem("Mensagem_Teste_03");
		contatoDao.salvar(contato3);
	}

	@Test
	public void testContatoDao() throws SQLException, Exception {
		beginContato();
		gravaContato();
		closeContato();

		// Carregamento do estado atual do banco de dados.
		IDataSet dataBase = getConnection().createDataSet();
		ITable tabelaAtual = dataBase.getTable("contato");

		// Carregamento do arquivo de controle (Contato.xml)
		IDataSet dataBaseXML = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(new File("control/contato.xml")));
		ITable tabelaControle = dataBaseXML.getTable("contato");

		Assertion.assertEquals(tabelaControle, tabelaAtual);

	}
}
