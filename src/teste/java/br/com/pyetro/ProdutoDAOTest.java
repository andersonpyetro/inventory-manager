package teste.java.br.com.pyetro;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.pyetro.dao.IProdutoDAO;
import br.com.pyetro.dao.ProdutoDAO;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class ProdutoDAOTest {
	
	private IProdutoDAO produtoDao;
	
	public ProdutoDAOTest() {
		produtoDao = new ProdutoDAO();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Produto> list = produtoDao.buscarTodos();
		list.forEach(prod -> {
			try {
				produtoDao.excluir(prod.getCodigo());
			} catch (DAOException e) {
				System.err.println("Erro ao excluir o produto: ");
			}
		});
	}
	
	private Produto criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produto.setPeso(15);
		produtoDao.cadastrar(produto);
		return produto;
	}
	
	private void excluir(String valor) throws DAOException {
		this.produtoDao.excluir(valor);
	}
	
	@Test
	public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
		Produto produto = criarProduto("A1");
		Assert.assertNotNull(produto);
		Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
		Assert.assertNotNull(produtoDB);
		excluir(produtoDB.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = criarProduto("A2");
		Assert.assertNotNull(produto);
		excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProduto("A4");
		produto.setNome("Anderson Pyetro");
		produtoDao.alterar(produto);
		Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
		Assert.assertNotNull(produtoDB);
		
		excluir(produto.getCodigo());
		Produto produtoDB1 = this.produtoDao.consultar(produto.getCodigo());
		assertNull(produtoDB1);
	}
	
	@Test
	public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
		criarProduto("A5");
		criarProduto("A6");
		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		for (Produto prod : list) {
			excluir(prod.getCodigo());
		}
		
		list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 0);
	}

}
