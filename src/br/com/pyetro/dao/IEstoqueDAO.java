package br.com.pyetro.dao;

import br.com.pyetro.domain.Estoque;
import br.com.pyetro.domain.Venda;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;
import br.com.pyetro.service.generic.IGenericService;

public interface IEstoqueDAO extends IGenericService<Estoque, Long>{
	
	public void devolverProdutosCancelados(Venda venda, Estoque estoque); 
	
	public void removerProdCompraAprovada(Venda venda, Estoque estoque);
		

}
