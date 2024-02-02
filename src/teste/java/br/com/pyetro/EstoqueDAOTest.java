package teste.java.br.com.pyetro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.pyetro.dao.EstoqueDAO;
import br.com.pyetro.dao.ProdutoDAO;
import br.com.pyetro.dao.VendaDAO;
import br.com.pyetro.dao.jdbc.ConnectionFactory;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.domain.Estoque;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.domain.Venda;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class EstoqueDAOTest {

	private EstoqueDAO estoqueDao;
	private ProdutoDAO produtoDao;
	private Produto produto;
	private VendaDAO vendaDao;
	private Venda venda;
	private Cliente cliente;

	public EstoqueDAOTest() {
		estoqueDao = new EstoqueDAO();
		produtoDao = new ProdutoDAO();
		vendaDao = new VendaDAO();
	}

	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testCadastrarEstoque()
			throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
		Estoque estoque = new Estoque();
		estoque.setProduto(produto);
		estoque.setQuantidade(10);

		assertTrue(estoqueDao.cadastrar(estoque));

		String codigoProduto = String.valueOf(produto.getCodigo());
		Long codigoProdutoLong = Long.parseLong(codigoProduto);
		Estoque estoqueConsultado = estoqueDao.consultar(codigoProdutoLong);
		assertNotNull(estoqueConsultado);
		assertEquals(produto.getCodigo(), estoqueConsultado.getCodigo());
		assertEquals(10, estoqueConsultado.getQuantidade());
	}

	@Test
	public void testExcluirEstoque(String valor)
			throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Estoque estoque = new Estoque();
		estoque.setProduto(produto);
		estoque.setQuantidade(10);

		assertTrue(estoqueDao.cadastrar(estoque));

		String codigoProduto = String.valueOf(produto.getCodigo());
		Long codigoProdutoLong = Long.parseLong(codigoProduto);
		estoqueDao.excluir(codigoProdutoLong);

		Estoque estoqueConsultado = estoqueDao.consultar(codigoProdutoLong);
		assertNull(estoqueConsultado);

	}



	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws DAOException {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e1) {
			throw new DAOException("ERRO AO FECHAR CONEXAO ", e1);
		}

	}

	private Connection getConnection() throws DAOException {
		try {
			return ConnectionFactory.getConnection();
		} catch (SQLException e) {
			throw new DAOException("ERRO ABRINDO CONEXAO COM BANCO DE DADOS ", e);
		}
	}

}
