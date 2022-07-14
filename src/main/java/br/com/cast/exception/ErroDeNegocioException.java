package br.com.cast.exception;

public class ErroDeNegocioException extends RuntimeException{
	
	public ErroDeNegocioException(String message) {
		super(message);
	}
}
