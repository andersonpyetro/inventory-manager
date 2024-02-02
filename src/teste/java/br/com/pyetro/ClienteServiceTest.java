package teste.java.br.com.pyetro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pyetro.dao.IClienteDAO;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;
import br.com.pyetro.services.ClienteService;
import br.com.pyetro.services.IClienteService;
import teste.java.br.com.pyetro.dao.ClienteDaoMock;

public class ClienteServiceTest {
	
	private IClienteService clienteService;
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Anderson");
		cliente.setCidade("Campina Grande");
		cliente.setEstado("Paraiba");
		cliente.setEndereco("End");
		cliente.setNumero(10);
		cliente.setTel(83999999999L);
	}
	@Test
	public void pesquisarCliente() throws DAOException {
		Cliente clienteConsultado = clienteService.buscaPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	@Test
	public void excluirCliente() throws DAOException {
		clienteService.excluir(cliente.getCpf());
	}
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		cliente.setNome("Anderson Pyetro");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Anderson Pyetro", cliente.getNome());
	}

}
