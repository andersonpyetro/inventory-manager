package br.com.pyetro.services;


import br.com.pyetro.dao.IProdutoDAO;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService{

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
		
	}

}
