package br.com.pyetro.dao;

import br.com.pyetro.domain.Venda;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {
	
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
 
}
