package teste.java.br.com.pyetro;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pyetro.dao.IProdutoDAO;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;
import br.com.pyetro.services.IProdutoService;
import br.com.pyetro.services.ProdutoService;
import teste.java.br.com.pyetro.dao.ProdutoDaoMock;

public class ProdutoServiceTest {
	
	private IProdutoService produtoService;
	
	private Produto produto;
	
	public ProdutoServiceTest() {
		IProdutoDAO dao = new ProdutoDaoMock();
		produtoService = new ProdutoService(dao);
	}
	
	@Before
	public void init() {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produto.setPeso(15);
	}
	
	@Test
	public void pesquisar() throws DAOException, MaisDeUmRegistroException, TableException {
		Produto produtor = this.produtoService.consultar(produto.getCodigo());
		Assert.assertNotNull(produtor);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = produtoService.cadastrar(produto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() throws DAOException {
		produtoService.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		produto.setNome("Anderson Pyetro");
		produtoService.alterar(produto);
		
		Assert.assertEquals("Anderson Pyetro", produto.getNome());
	}
	

}
