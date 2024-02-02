package teste.java.br.com.pyetro;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.pyetro.dao.ClienteDAO;
import br.com.pyetro.dao.IClienteDAO;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {
	
	private IClienteDAO clienteDao;
	
	public ClienteDAOTest(){
		clienteDao = new ClienteDAO();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Cliente> list = clienteDao.buscarTodos(); 
		list.forEach(cli -> {
			try {
				clienteDao.buscarTodos();
			} catch (DAOException e) {
				
				e.printStackTrace();
			}
			
		});	
		
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12312312323L);
		cliente.setNome("Anderson");
		cliente.setSobrenome("Oliveira");
		cliente.setCidade("Campina Grande");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(83111111111L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente.getCpf());
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12312312323L);
		cliente.setNome("Anderson");
		cliente.setSobrenome("Oliveira");
		cliente.setCidade("Campina Grande");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(83111111111L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente.getCpf());
		clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNull(clienteConsultado);
	}
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12312312323L);
		cliente.setNome("Anderson");
		cliente.setSobrenome("Oliveira");
		cliente.setCidade("Campina Grande");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(83111111111L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Anderson Pyetro");
		clienteDao.alterar(clienteConsultado);
		
		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Anderson Pyetro", clienteAlterado.getNome());
		
		clienteDao.excluir(cliente.getCpf());
		clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNull(clienteAlterado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12312312323L);
		cliente.setNome("Anderson");
		cliente.setSobrenome("Oliveira");
		cliente.setCidade("Campina Grande");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(83111111111L);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
		Cliente cliente1 = new Cliente();
		cliente.setCpf(12312312323L);
		cliente.setNome("Anderson");
		cliente.setSobrenome("Nobrega");
		cliente.setCidade("Campina Grande");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(83111111111L);
		Boolean retorno1 = clienteDao.cadastrar(cliente1);
		Assert.assertTrue(retorno1);
		
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli.getCpf());
			}catch (DAOException e) {
				e.printStackTrace();
			}
			
		});
		
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}

}
