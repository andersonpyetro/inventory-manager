package br.com.pyetro.dao;

import java.io.Serializable;
import java.util.Collection;

import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public interface IGenericDAO <T extends Persistente, E extends Serializable> {
	
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;
	public void excluir (E valor) throws DAOException;
	public void alterar (T entity) throws TipoChaveNaoEncontradaException, DAOException;
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException;
	public Collection<T> buscarTodos() throws DAOException;

}
