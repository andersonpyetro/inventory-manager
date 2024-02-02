package teste.java.br.com.pyetro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.pyetro.dao.IClienteDAO;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class ClienteDaoMock implements IClienteDAO{
	
	private List<Cliente> clientesMockados;
	
	public ClienteDaoMock() {
		this.clientesMockados = new ArrayList<>();
	}

	@Override
	public Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException, DAOException {
		if (clientesMockados.contains(cliente)) {
			throw new DAOException("CLIENTE JA CADASTRADO! ", null);
		}
		
		clientesMockados.add(cliente);
			
		return true;
	}

	@Override
	public void excluir(Long codigo) throws DAOException {		
		Cliente clienteEncontrado = null;
		for(Cliente cliente : clientesMockados) {
			if(cliente.getCpf().equals(codigo)) {
				 clienteEncontrado = cliente;
				break;
			}			
		}
		
		if ( clienteEncontrado != null) {
			clientesMockados.remove( clienteEncontrado);
		} else {
			throw new DAOException("CLIENTE NAO ENCONTRADO PARA EXCLUSAO ", null);
		}
	}	

	@Override
	public void alterar(Cliente entity) throws TipoChaveNaoEncontradaException, DAOException {
		boolean clienteEncontrado = false;
		for (Cliente cliente : clientesMockados) {
			if(cliente.getCpf().equals(entity.getCpf())) {
				cliente.setNome(entity.getNome());
				cliente.setSobrenome(entity.getSobrenome());
				cliente.setCidade(entity.getCidade());
	            cliente.setEndereco(entity.getEndereco());
	            cliente.setEstado(entity.getEstado());
	            cliente.setTel(entity.getTel());
	            cliente.setNumero(entity.getNumero());
	            clienteEncontrado = true;
	            break;
				
			}
		}
		
		if (!clienteEncontrado) {
			throw new DAOException("CLIENTE NAO ENCONTRADO PARA ALTERACAO ", null);
		}
	
	}

	@Override
	public Cliente consultar(Long codigo) throws MaisDeUmRegistroException, TableException, DAOException {
		Cliente clienteConsultado = null;
		for(Cliente cliente : clientesMockados) {
			if (cliente.getCpf().equals(codigo)) {
				clienteConsultado = cliente;
				break;
			}
		}
		if (clienteConsultado == null) {
			throw new DAOException("CLIENTE NAO ENCONTRADO O CPF: " + codigo, null);
		}
		return clienteConsultado;
	}

	@Override
	public Collection<Cliente> buscarTodos() throws DAOException {
		
		return clientesMockados;
	}

}
