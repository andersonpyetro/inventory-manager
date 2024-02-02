package br.com.pyetro.services;

import br.com.pyetro.dao.IClienteDAO;
import br.com.pyetro.dao.IGenericDAO;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;
import br.com.pyetro.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService{
	
	private IClienteDAO clienteDAO;

	public ClienteService(IGenericDAO<Cliente, Long> dao) {
		super(dao);
		this.clienteDAO = clienteDAO;
	}

	@Override
	public Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException, DAOException {
	
		return clienteDAO.cadastrar(cliente);
	}


	@Override
	public void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException, DAOException {
		clienteDAO.alterar(cliente);
		
	}

	@Override
	public Cliente consultar(Long cpf) throws MaisDeUmRegistroException, TableException, DAOException {
		
		return this.clienteDAO.consultar(cpf);
	}

	@Override
	public Cliente buscaPorCPF(Long cpf) throws DAOException {
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			throw new DAOException("CPF NAO ENCONTRADO ", e);
		}
	}

	@Override
	public void excluir(long cpf) throws DAOException {
		clienteDAO.excluir(cpf);
		
	}

}
