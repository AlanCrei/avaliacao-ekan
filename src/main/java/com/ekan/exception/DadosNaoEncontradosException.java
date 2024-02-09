package com.ekan.exception;

public class DadosNaoEncontradosException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DadosNaoEncontradosException(String mensagem) {
		super(mensagem);	
	}	
}
