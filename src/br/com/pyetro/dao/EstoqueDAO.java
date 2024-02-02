package br.com.pyetro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import br.com.pyetro.domain.Estoque;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.domain.ProdutoQuantidade;
import br.com.pyetro.domain.Venda;
import br.com.pyetro.domain.Venda.Status;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class EstoqueDAO extends GenericDAO<Estoque, Long> implements IEstoqueDAO {

	@Override
	public Boolean cadastrar(Estoque entity) throws TipoChaveNaoEncontradaException, DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO TB_ESTOQUE (codigo, id_produto_fk, quantidade, data_entrada, custo) VALUES (?, ?, ?, ?, ?)";
			connection = getConnection();
			stm = connection.prepareStatement(sql);
			stm.setLong(1, entity.getCodigo());
			stm.setLong(2, entity.getProduto().getId());
			stm.setInt(3, entity.getQuantidade());
			stm.setTimestamp(3, Timestamp.from(entity.getEntradaEstoque()));
			stm.setBigDecimal(5, entity.getCusto());
			
			int rowsAffected = stm.executeUpdate();
			
			if(rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao cadastrar o registro de estoque ", e);
		}
	}

	@Override
	public void excluir(Long valor) throws DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			String sql = "DELETE FROM TB_ESTOQUE WHERE id = ?";
			connection = getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setLong(1, valor);
			return true;
			
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException ("Erro ao excluir o registro de estoque", e);
		}

	}

	@Override
	public void alterar(Estoque entity) throws TipoChaveNaoEncontradaException, DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = getConnection();
			String sql = "UPDATE TB_ESTOQUE SET CODIGO = ?, QUANTIDADE =?, WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			
			stm.setLong(1, entity.getCodigo());
			stm.setInt(2, entity.getQuantidade());
			stm.setLong(3, entity.getId());
			
			stm.executeUpdate();
			
			stm.close();
			connection.close();			
			
		} catch (SQLException e) {
			throw new DAOException("Erro ao alterar o estoque: ", e);
		}

	}

	@Override
	public Estoque consultar(Long codigo) throws DAOException, MaisDeUmRegistroException, TableException {
		Connection connection = null;
		PreparedStatement stm = null;
		Estoque estoque = null;
		
		try {
			connection = getConnection();
			String sql = "SELECT * FROM TB_ESTOQUE WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			
			stm.setLong(1, codigo);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				throw new MaisDeUmRegistroException("Mais de um registro encontrado para o ID: " + codigo);
			} else {
				estoque = new Estoque();
				estoque.setId(rs.getLong("id"));
				estoque.setCodigo(rs.getLong("codigo"));
				estoque.setQuantidade(rs.getInt("quantidade"));
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new DAOException("ERRO AO CONSULTAR O ESTOQUE", null);
		}
		return estoque;
	}

	@Override
	public Class<Estoque> getTipoClasse() {
		
		return Estoque.class;
	}

	@Override
	public void atualizarDados(Estoque entity, Estoque entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setId(entity.getId());
		entityCadastrado.setCusto(entity.getCusto());
		entityCadastrado.setEntradaEstoque(entity.getEntradaEstoque());
		entityCadastrado.setSaidaEstoque(entity.getSaidaEstoque());
		entityCadastrado.setQuantidade(entity.getQuantidade());

	}

	@Override
	protected String getQueryInsercao() {

		return "INSERT INTO TB_ESTOQUE(codigo, id_produto_fk, quantidade, data_entrada, data_saida, custo) VALUES (?,?,?,?,?,?)";
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_ESTOQUE WHERE id =?";
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_ESTOQUE ");
		sb.append("SET QUANTIDADE = ?, ");
		sb.append("CODIGO = ?, ");
		sb.append("ID = ?, ");
		sb.append("CUSTO = ?, ");
		sb.append("DATA_ENTRADA = ?, ");
		sb.append("DATA_SAIDA = ?, ");
		sb.append("WHERE ID = ? ");
		
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque entity) throws SQLException {
		stmInsert.setLong(1, entity.getCodigo());
		stmInsert.setLong(2, entity.getProduto().getId());
		stmInsert.setInt(3, entity.getQuantidade());
		stmInsert.setTimestamp(4, Timestamp.from(entity.getEntradaEstoque()));
		stmInsert.setBigDecimal(5, entity.getCusto());
		stmInsert.setTimestamp(6, Timestamp.from(entity.getSaidaEstoque()));

	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
		stmDelete.setLong(1, valor);

	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque entity) throws SQLException {
		stmUpdate.setInt(1, entity.getQuantidade());
		stmUpdate.setLong(2, entity.getCodigo());
		stmUpdate.setLong(3, entity.getId());
		stmUpdate.setBigDecimal(4, entity.getCusto());
		stmUpdate.setTimestamp(5, Timestamp.from(entity.getEntradaEstoque()));
		stmUpdate.setTimestamp(6, Timestamp.from(entity.getSaidaEstoque()));

	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);

	}

	@Override
	public void devolverProdutosCancelados(Venda venda, Estoque estoque) {
		Status statusVenda = venda.getStatus();
		if (statusVenda == Status.CANCELADA) {
			Map<Produto, Integer> produtosNoEstoque = estoque.getProdutos();
			for (ProdutoQuantidade prod : venda.getProdutos()) {
				Produto produto = prod.getProduto();
				int quantidadeDevolvida = prod.getQuantidade();

				if (produtosNoEstoque.containsKey(produto)) {
					atualizarQuantidadeNoEstoque(produto, quantidadeDevolvida, produtosNoEstoque);

				} else {
					adicionarProdutoNoEstoque(produto, quantidadeDevolvida, produtosNoEstoque);
				}
			}
		}

	}

	private void adicionarProdutoNoEstoque(Produto produto, int quantidadeDevolvida,
			Map<Produto, Integer> produtosNoEstoque) {
		if (produtosNoEstoque.containsKey(produto)) {
			int quantidadeAtual = produtosNoEstoque.get(produto);
			produtosNoEstoque.put(produto, quantidadeAtual + quantidadeDevolvida);
		}

	}

	private void atualizarQuantidadeNoEstoque(Produto produto, int quantidadeDevolvida,
			Map<Produto, Integer> produtosNoEstoque) {
		int quantidadeAtual = produtosNoEstoque.get(produto);
		produtosNoEstoque.put(produto, quantidadeAtual + quantidadeDevolvida);

	}

	@Override
	public void removerProdCompraAprovada(Venda venda, Estoque estoque) {
		Status statusVenda = venda.getStatus();
		if (statusVenda == Status.CONCLUIDA) {
			for (ProdutoQuantidade prod : venda.getProdutos()) {
				Produto produto = prod.getProduto();
				int quantidadeVendida = prod.getQuantidade();

				if (estoque.produtoExisteNoEstoque(produto)) {
					estoque.removerProduto(produto, quantidadeVendida);
				}
			}
		}

	}

}
