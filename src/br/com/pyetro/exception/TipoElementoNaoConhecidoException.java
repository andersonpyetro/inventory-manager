package br.com.pyetro.exception;

public class TipoElementoNaoConhecidoException extends Exception{
	private static final long serialVersionUID = 7251136772838080583L;
	
	public TipoElementoNaoConhecidoException(String msg) {
		this(msg, null);
	}

	public TipoElementoNaoConhecidoException(String msg, Throwable  e) {
		super(msg, e);
	}
		

}
