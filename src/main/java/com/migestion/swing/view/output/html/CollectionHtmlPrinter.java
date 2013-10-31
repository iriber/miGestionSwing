package com.migestion.swing.view.output.html;

import com.migestion.swing.model.IModelToPrint;
import com.migestion.swing.resources.PropertiesImage;
import com.migestion.swing.resources.PropertiesReport;
import com.migestion.swing.view.output.helpers.CollectionToHtml;


/**
 * Clase para imprimir un listado en formato html.
 * Para poder imprimir nuestro listado de objetos se
 * debe definir una subclse de ElementTableModel.
 * 
 * @author Bernardo Iribarne
 *
 */
public class CollectionHtmlPrinter extends ObjectHtmlPrinter{

	public CollectionHtmlPrinter(String title) {
		super(title);		
	}

	/**
	 * nombre del template html
	 * @return
	 */
	public String getTemplateName() {			
		return PropertiesReport.LIST_COLLECTION;
	}

	/**
	 * argumentos a ser reemplazados en el template html
	 * @param object
	 * @return
	 */
	public Object[] getTemplateArguments(Object object) {
		IModelToPrint model = (IModelToPrint)object;
		Object[] argumentos = new Object[]{
				this.title,
				PropertiesImage.report_list_collection,
				model.getDescription(),
				CollectionToHtml.collectionToHtml(model)				
		};
		return argumentos;
	}
	
	/**
	 * retorna el nombre del reporte
	 * @return
	 */
	public String getNameReport(Object object){
		return  "list_" + ".html";
	}
	
}
