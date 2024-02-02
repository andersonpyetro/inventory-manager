package br.com.pyetro.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pyetro.domain.Estoque;
import br.com.pyetro.domain.Produto;

public class EstoqueFactory {
	
	public static Estoque createFromResultSet(ResultSet rs) throws SQLException {
		Estoque estoque = new Estoque();
		
		estoque.setId(rs.getLong("id"));
		estoque.setCodigo(rs.getLong("codigo"));
		
		Produto produto = new Produto();
		produto.setId(rs.getLong("id_produto_fk"));
		produto.setNome(rs.getString("nome_produto"));		
		
		int quantidade = rs.getInt("quantidade");
		estoque.adicionarProduto(produto, quantidade);
		
		return estoque;
	}
	

}
