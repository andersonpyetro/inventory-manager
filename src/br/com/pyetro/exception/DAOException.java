package br.com.pyetro.exception;

public class DAOException extends Exception{

	private static final long serialVersionUID = -2116049160336653416L;

	public DAOException(String msg, Exception e) {
		super(msg, e);
	}

}
