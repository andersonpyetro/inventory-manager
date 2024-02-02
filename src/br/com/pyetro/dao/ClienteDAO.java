package br.com.pyetro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pyetro.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {
	
	public ClienteDAO() {
		super();
	}

	@Override
	public Class<Cliente> getTipoClasse() {
		
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		entityCadastrado.setCidade(entity.getCidade());
		entityCadastrado.setCpf(entity.getCpf());
		entityCadastrado.setEstado(entity.getEstado());
		entityCadastrado.setEndereco(entity.getEndereco());
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setNumero(entity.getNumero());
		entityCadastrado.setTel(entity.getTel());
		entityCadastrado.setSobrenome(entity.getSobrenome());
		
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_CLIENTE ");
		sb.append("ID, NOME, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO, SOBRENOME ");
		sb.append("VALUES (nextval('sq_cliente'),?,?,?,?,?,?,?,?)");
		
		return sb.toString();
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_CLIENTE WHERE CPF = ? ";
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_CLIENTE");
		sb.append("SET NOME = ?,");
		sb.append("TEL =?,");
		sb.append("ENDERECO =?,");
		sb.append("CIDADE =?,");
		sb.append("ESTADO =?,");
		sb.append("SOBRENOME =?,");
		sb.append(" WHERE CPF = ?,");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente entity) throws SQLException {
		stmInsert.setString(1, entity.getNome());
		stmInsert.setLong(2, entity.getCpf());
		stmInsert.setLong(3, entity.getTel());
		stmInsert.setString(4, entity.getEndereco());
		stmInsert.setLong(5, entity.getNumero());
		stmInsert.setString(6, entity.getCidade());
		stmInsert.setString(7, entity.getEstado());
		stmInsert.setString(8, entity.getSobrenome());
		
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
		stmDelete.setLong(1, valor);
		
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente entity) throws SQLException {
		stmUpdate.setString(1, entity.getNome());
		stmUpdate.setLong(2, entity.getTel());
		stmUpdate.setString(3, entity.getEndereco());
		stmUpdate.setLong(4, entity.getNumero());
		stmUpdate.setString(5, entity.getCidade());
		stmUpdate.setString(6, entity.getEstado());
		stmUpdate.setLong(7, entity.getCpf());
		stmUpdate.setString(8, entity.getSobrenome());
		
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
		
	}

	
}
