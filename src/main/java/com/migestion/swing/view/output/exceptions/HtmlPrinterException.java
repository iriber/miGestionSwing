package com.migestion.swing.view.output.exceptions;

/**
 * Excepci�n para manejar los errores y/o validaciones
 * en los di�logos/ventanas
 * 
 * @author Bernardo Iribarne
 *
 */
public class HtmlPrinterException extends Exception{

	public HtmlPrinterException(String message){
		super(message);
	}

	public HtmlPrinterException(Throwable cause){
		super(cause);
	}

}
