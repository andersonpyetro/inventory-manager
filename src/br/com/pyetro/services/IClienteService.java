package br.com.pyetro.services;

import br.com.pyetro.dao.IGenericDAO;
import br.com.pyetro.domain.Cliente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public interface IClienteService extends IGenericDAO<Cliente, Long> {
	
	Boolean cadastrar (Cliente cliente) throws TipoChaveNaoEncontradaException, DAOException;
	
	Cliente buscaPorCPF(Long cpf) throws DAOException;
	
	void excluir (long cpf) throws DAOException;
	
	void alterar (Cliente cliente) throws TipoChaveNaoEncontradaException, DAOException;

}
