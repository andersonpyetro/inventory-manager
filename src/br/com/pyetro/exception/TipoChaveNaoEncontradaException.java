package br.com.pyetro.exception;

public class TipoChaveNaoEncontradaException extends Exception{
	private static final long serialVersionUID = -4171206260213588636L;
	
	
	public TipoChaveNaoEncontradaException(String msg) {
		this(msg, null);
	}

	public TipoChaveNaoEncontradaException(String msg, Throwable e) {
		super(msg, e);
	}

}
