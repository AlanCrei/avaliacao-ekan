package com.ekan.exception;

public class ErroPersistenciaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ErroPersistenciaException(String mensagem) {
		super(mensagem);
	}	
}
