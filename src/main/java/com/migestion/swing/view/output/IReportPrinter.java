package com.migestion.swing.view.output;

import com.migestion.swing.model.UICollection;
import com.migestion.swing.view.output.exceptions.ReportPrinterException;

/**
 * Interfaz que deber�n definir aquellas clases que impriman
 * UICollections.
 * 
 * @see com.ostrich.commonui.model.UICollection
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public interface IReportPrinter {
	
	
	/**
	 * Imprime la colecci�n <code>collection</code>
	 * @param collection
	 * @throws ReportPrinterException
	 */
	public void print(UICollection collection) throws ReportPrinterException ;

}
