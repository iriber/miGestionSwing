package com.migestion.swing.view.output.html;


import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import com.migestion.swing.resources.PropertiesReport;
import com.migestion.swing.utils.FileUtils;
import com.migestion.swing.view.output.exceptions.HtmlPrinterException;


/**
 * Clase para imprimir en formato Html un objeto , a 
 * a partir de un template html.
 * 
 * Para cada objeto particular se debe generar una subclase 
 * donde se defina el nombre del template a utilizar
 * para generar el reporte.
 * 
 * @author Bernardo Iribarne
 *
 */
public abstract class ObjectHtmlPrinter {
	
	protected String title;
	
	public ObjectHtmlPrinter(String title){
		this.title = title;
	}
	
	/**
	 * Imprime un objeto en formato Html
	 * 
	 * @param object
	 * @throws HtmlPrinterException
	 */
	public void print(Object object) throws HtmlPrinterException{		
		
		try {			
			String templatePath = PropertiesReport.TEMPLATE_PATH;
			String reportesPath = PropertiesReport.REPORTS_PATH;
			//tomamos el template html
			String bodyTemplate = FileUtils.cargarTextoDesdeArchivo(templatePath+getTemplateName());
			//formateamos el template
			String body = MessageFormat.format(bodyTemplate, getTemplateArguments(object));
			//creamos el archivo con el template formateado
			FileUtils.crearArchivo(reportesPath, getNameReport(object), body, false);
			//abrimos el archivo generado
			//obtenemos el path actual
			File file = new File("");
			String url = "file://localhost/" +  file.getAbsolutePath()+"\\"+reportesPath+getNameReport(object);
			//FileUtils.ejecutaCommando("cmd /c start " + openWith() + " file://localhost/"+url, null, null);
			FileUtils.abrirArchivo(url);
		} catch (IOException e) {
			throw new HtmlPrinterException(e);
		}
	}

	/**
	 * nombre del template html
	 * @return
	 */
	public abstract String getTemplateName();

	/**
	 * argumentos a ser reemplazados en el template html
	 * @param object
	 * @return
	 */
	public abstract Object[] getTemplateArguments(Object object);
	
	
	/**
	 * retorna el nombre del reporte
	 * @return
	 */
	public abstract String getNameReport(Object object);
	
	/**
	 * t�tulo del reporte
	 * @return
	 */
	public String getTitle(){
		return this.title;
	}
}
