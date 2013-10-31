package com.migestion.swing.view.output;

import com.migestion.swing.view.output.exceptions.ReportPrinterException;


/**
 * Interfaz que deberán definir aquellas clases que impriman
 * un objeto.
 * 
 * 
 * @author Bernardo Iribarne
 *
 */
public interface IObjectPrinter {

	
	/**
	 * Imprime el objeto <code>object</code>
	 * @param object
	 * @throws ReportPrinterException
	 */
	public void print(Object object) throws ReportPrinterException ;
	
}
