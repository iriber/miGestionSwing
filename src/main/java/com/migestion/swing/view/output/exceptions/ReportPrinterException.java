package com.migestion.swing.view.output.exceptions;

/**
 * Excepción para manejar los errores al imprimir reportes.
 * 
 * @author Bernardo Iribarne
 *
 */
public class ReportPrinterException extends Exception{

	public ReportPrinterException(String message){
		super(message);
	}

	public ReportPrinterException(Throwable cause){
		super(cause);
	}

}
