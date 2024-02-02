package br.com.pyetro.domain;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import anotacao.TipoChave;
import br.com.pyetro.dao.Persistente;

@Tabela("TB_CLIENTE")
public class Cliente implements Persistente{

	@ColunaTabela(dbName = "id", setJavaName = "setId")
	private Long id;

	@ColunaTabela(dbName = "nome", setJavaName = "setNome")
	private String nome;

	@TipoChave("getCpf")
	@ColunaTabela(dbName = "cpf", setJavaName = "setCpf")
	private Long cpf;

	@ColunaTabela(dbName = "tel", setJavaName = "setTel")
	private Long tel;

	@ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
	private String endereco;

	@ColunaTabela(dbName = "numero", setJavaName = "setNumero")
	private Integer numero;

	@ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
	private String cidade;

	@ColunaTabela(dbName = "estado", setJavaName = "setEstado")
	private String estado;
	
	@ColunaTabela(dbName = "sobrenome", setJavaName = "setSobrenome")
	private String sobrenome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
