package teste.java.br.com.pyetro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.pyetro.dao.IProdutoDAO;
import br.com.pyetro.domain.Produto;
import br.com.pyetro.exception.DAOException;
import br.com.pyetro.exception.MaisDeUmRegistroException;
import br.com.pyetro.exception.TableException;
import br.com.pyetro.exception.TipoChaveNaoEncontradaException;

public class ProdutoDaoMock implements IProdutoDAO{
	
	private List<Produto> produtosMockados;
	
	public ProdutoDaoMock() {
		this.produtosMockados = new ArrayList<>();
	}

	@Override
	public Boolean cadastrar(Produto produto) throws TipoChaveNaoEncontradaException, DAOException {
		if (produtosMockados.contains(produto)) {
			throw new DAOException("PRODUTO JA CADASTRADO! ", null);
		}
		produtosMockados.add(produto);
		
		return true;
	}

	@Override
	public void excluir(String codigo) throws DAOException {
		Produto produtoEncontrado = null;
		for (Produto produto : produtosMockados) {
			if (produto.getCodigo().equals(codigo)) {
				produtoEncontrado = produto;	
				break;
			}
		}
		
		if(produtoEncontrado != null) {
			produtosMockados.remove(produtoEncontrado);
		} else {
			throw new DAOException ("PRODUTO NAO ENCONTRAODO ", null);
		}
		
	}

	@Override
	public void alterar(Produto entity) throws TipoChaveNaoEncontradaException, DAOException {
		boolean produtoEncontrado = false;
		for (Produto produto : produtosMockados) {
			if (produto.getCodigo().equals(entity.getCodigo())) {
				produto.setCodigo(entity.getCodigo());
				produto.setDescricao(entity.getDescricao());
				produto.setValor(entity.getValor());
				produto.setNome(entity.getNome());
				produto.setPeso(entity.getPeso());
				produtoEncontrado = true;
				break;
				
			}
		}
		
		if (!produtoEncontrado) {
			throw new DAOException ("PRODUTO NAO ENCONTRAODO ", null);
		}
		
	}

	@Override
	public Produto consultar(String codigo) throws MaisDeUmRegistroException, TableException, DAOException {
		Produto produtoConsultado = null;
		for(Produto produto : produtosMockados) {
			if (produto.getCodigo().equals(codigo)) {
				produtoConsultado = produto;
				break;
			}
		}
		
		if(produtoConsultado == null) {
			throw new DAOException ("PRODUTO NAO ENCONTRAODO ", null);
		}
		return produtoConsultado;
	}

	@Override
	public Collection<Produto> buscarTodos() throws DAOException {
		
		return produtosMockados;
	}

}
