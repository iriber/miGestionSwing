package com.migestion.swing.controller.exception;

/**
 * Excepción para manejar los errores y/o validaciones 
 * de los controladores
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public class ControllerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8796845175358265653L;

	public ControllerException(String message){
		super(message);
	}
	
	public ControllerException(Throwable cause){
		super(cause);
	}
}
