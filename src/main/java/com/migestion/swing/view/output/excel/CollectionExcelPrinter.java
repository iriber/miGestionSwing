package com.migestion.swing.view.output.excel;

import com.migestion.swing.view.output.html.CollectionHtmlPrinter;

/**
 * Clase para imprimir un listado en formato excel.
 * Para poder imprimir nuestro listado de objetos se
 * debe definir una subclse de ElementTableModel.
 * 
 * @author Bernardo Iribarne
 *
 */
public class CollectionExcelPrinter extends CollectionHtmlPrinter{

	
	public CollectionExcelPrinter(String title) {
		super(title);
	}

	/**
	 * retorna el nombre del reporte
	 * @return
	 */
	public String getNameReport(Object object){
		return  "list_" + ".xls";
	}
	
}
