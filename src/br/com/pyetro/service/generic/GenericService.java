package br.com.pyetro.service.generic;

import java.io.Serializable;
import java.util.Collection;

import br.com.pyetro.dao.IGenericDAO;
import br.com.pyetro.dao.Persistente;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public abstract class GenericService<T extends Persistente, E extends Serializable> implements IGenericService<T, E> {
	
	protected IGenericDAO<T, E> dao;

	public GenericService(IGenericDAO<T,E> dao) {
		this.dao = dao;
	}

	@Override
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(E valor) throws DAOException {
		this.dao.excluir(valor);
		
	}

	@Override
	public void alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		this.dao.alterar(entity);
		
	}

	@Override
	public T consultar(E valor) throws DAOException, MaisDeUmRegistroException, TableException {
		try {
			return this.dao.consultar(valor);			
		} catch (MaisDeUmRegistroException | TableException e ) {
			throw new DAOException("NAO FOI POSSIVEL ENCONTRAR O OBJETO ", e);
		}
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		return this.dao.buscarTodos();
	}
	
}
