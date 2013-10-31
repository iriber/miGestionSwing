package com.migestion.swing.navigation.exception;

/**
 * Excepción para manejar los errores y/o validaciones 
 * de los links
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkException extends Exception{

	public LinkException(String message){
		super(message);
	}
	
	public LinkException(Throwable cause){
		super(cause);
	}
}
