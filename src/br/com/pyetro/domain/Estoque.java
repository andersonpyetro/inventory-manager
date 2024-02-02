package br.com.pyetro.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import anotacao.TipoChave;
import br.com.pyetro.dao.Persistente;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente{
	
	public enum Status{
		
		EM_ESTOQUE, FORA_DE_ESTOQUE;
		
		public static Status getByName(String value) {
			for (Status status : Status.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}
	
	@ColunaTabela(dbName = "id", setJavaName = "setId")
	private Long id;
	
	@TipoChave("getCodigo")
	@ColunaTabela(dbName = "codigo", setJavaName = "setCodigo")
	private Long codigo;
	
	@ColunaTabela(dbName = "id_produto_fk", setJavaName = "setProduto")
	private Produto produto;
	
	@ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
	private int quantidade;
	
	@ColunaTabela(dbName = "data_entrada", setJavaName = "setEntradaEstoque")
	private Instant entradaEstoque;
	
	@ColunaTabela(dbName = "data_saida", setJavaName = "setSaidaEstoque")
	private Instant saidaEstoque;
	
	@ColunaTabela(dbName = "custo", setJavaName = "setCusto")
	private BigDecimal custo;
	
	@ColunaTabela(dbName = "id_venda_fk", setJavaName = "setVenda")
	private Venda venda;
	
	private Map<Produto, Integer> produtos;
	
	public Estoque() {
		this.produtos = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Instant getEntradaEstoque() {
		return entradaEstoque;
	}

	public void setEntradaEstoque(Instant entradaEstoque) {
		this.entradaEstoque = entradaEstoque;
	}

	public Instant getSaidaEstoque() {
		return saidaEstoque;
	}

	public void setSaidaEstoque(Instant saidaEstoque) {
		this.saidaEstoque = saidaEstoque;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Map<Produto, Integer> getProdutos() {
		return produtos;
	}
	
	public void adicionarProduto(Produto produto, int quantidade) {
		if (produtos.containsKey(produto)) {
			int quantidadeAtual = produtos.get(produto);
			produtos.put(produto, quantidadeAtual + quantidade);
		} else {
			produtos.put(produto, quantidade);
		}
	}
	
	public void removerProduto(Produto produto, int quantidade) {
		if(produtos.containsKey(produto)) {
			int quantidadeAtual = produtos.get(produto);
			if(quantidadeAtual>=quantidade) {
				produtos.put(produto, quantidadeAtual - quantidade);
			} else {
				System.out.println("Não há estoque suficiente para remover");
			}
		}
	}
	
	public BigDecimal custoTotalEstoque() {
		BigDecimal custoTotal = BigDecimal.ZERO;
		for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
			Produto produto = entry.getKey();
			Integer quantidade = entry.getValue();
			
			BigDecimal custoProdutos = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
			
			custoTotal = custoTotal.add(custoProdutos);
		}
		
		return custoTotal;
	}

	public boolean produtoExisteNoEstoque(Produto produto) {
		
		return produtos.containsKey(produto);
	}

}
