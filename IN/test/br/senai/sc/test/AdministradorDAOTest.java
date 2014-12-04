package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.gpe.entity.Administrador;

public class AdministradorDAOTest extends DBUnitTest {

	public AdministradorDAOTest() {
		super();
	}

	private void gravaAdministrador() {
		Administrador adiministrador1 = new Administrador();
		adiministrador1.setNome("Nome_Teste_01");
		adiministrador1.setCpf("CPF_Teste_01");
		adiministrador1.setDatanascimento("Data_Teste_01");
		adiministrador1.setEmail("Email_Teste_01");
		adiministrador1.setUsuario("Usuario_Teste_01");
		adiministrador1.setSenha("Senha_Teste_01");
		adiministrador1.setRg("Rg_Teste_01");
		adiministrador1.setPais("Pais_Teste_01");
		adiministrador1.setEstado("Estado_Teste_01");
		adiministrador1.setCidade("Cidade_Teste_01");
		adiministrador1.setBairro("Bairro_Teste_01");
		adiministrador1.setRua("Rua_Teste_01");
		adiministrador1.setComplemento("Complemento_Teste_01");
		adiministrador1.setTelefone("Telefone_Teste_01");
		adiministrador1.setSexo("Sexo_Teste_01");
		administradorDao.salvar(adiministrador1);

		Administrador adiministrador2 = new Administrador();
		adiministrador2.setNome("Nome_Teste_02");
		adiministrador2.setCpf("CPF_Teste_02");
		adiministrador2.setDatanascimento("Data_Teste_02");
		adiministrador2.setEmail("Email_Teste_02");
		adiministrador2.setUsuario("Usuario_Teste_02");
		adiministrador2.setSenha("Senha_Teste_02");
		adiministrador2.setRg("Rg_Teste_02");
		adiministrador2.setPais("Pais_Teste_02");
		adiministrador2.setEstado("Estado_Teste_02");
		adiministrador2.setCidade("Cidade_Teste_02");
		adiministrador2.setBairro("Bairro_Teste_02");
		adiministrador2.setRua("Rua_Teste_02");
		adiministrador2.setComplemento("Complemento_Teste_02");
		adiministrador2.setTelefone("Telefone_Teste_02");
		adiministrador2.setSexo("Sexo_Teste_02");
		administradorDao.salvar(adiministrador2);

		Administrador adiministrador3 = new Administrador();
		adiministrador3.setNome("Nome_Teste_03");
		adiministrador3.setCpf("CPF_Teste_03");
		adiministrador3.setDatanascimento("Data_Teste_03");
		adiministrador3.setEmail("Email_Teste_03");
		adiministrador3.setUsuario("Usuario_Teste_03");
		adiministrador3.setSenha("Senha_Teste_03");
		adiministrador3.setRg("Rg_Teste_03");
		adiministrador3.setPais("Pais_Teste_03");
		adiministrador3.setEstado("Estado_Teste_03");
		adiministrador3.setCidade("Cidade_Teste_03");
		adiministrador3.setBairro("Bairro_Teste_03");
		adiministrador3.setRua("Rua_Teste_03");
		adiministrador3.setComplemento("Complemento_Teste_03");
		adiministrador3.setTelefone("Telefone_Teste_03");
		adiministrador3.setSexo("Sexo_Teste_03");
		administradorDao.salvar(adiministrador3);
	}

	@Test
	public void testAdministradorDao() throws SQLException, Exception {
		beginAdministrador();
		gravaAdministrador();
		closeAdministrador();

		// Carregamento do estado atual do banco de dados.
		IDataSet dataBase = getConnection().createDataSet();
		ITable tabelaAtual = dataBase.getTable("administrador");

		// Carregamento do arquivo de controle (anuncio.xml)
		IDataSet dataBaseXML = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(
						new File("control/administrador.xml")));
		ITable tabelaControle = dataBaseXML.getTable("administrador");

		Assertion.assertEquals(tabelaControle, tabelaAtual);

	}
}
