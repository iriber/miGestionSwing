package com.migestion.swing.view.output.jasper;

import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.migestion.swing.view.output.IObjectPrinter;
import com.migestion.swing.view.output.exceptions.ReportPrinterException;

/**
 * Imprime un objeto con Jasper Report.
 * Deber� subclasificarse para definir el path del xml y los par�metros del reporte.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public abstract class JasperObjectPrinter implements IObjectPrinter{

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.view.output.IObjectPrinter#print(java.lang.Object)
	 */
	public void print(Object object) throws ReportPrinterException {
		
		//creamos el data source con la colecci�n.
		System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");  
		
		try {
			//compilamos el reporte.
			JasperReport jasperReport = JasperCompileManager.compileReport(getXMLPath());
		    //formamos el reporte.
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, getParameters(object));
		    //visualizamos el reporte.
			JasperViewer.viewReport(jasperPrint, false);
		    
		} catch (JRException e) {
			throw new ReportPrinterException(e);
		}
	}

	/**
	 * @return path donde se encuentra el xml del reporte.
	 */
	public abstract String getXMLPath();
	
	/**
	 * @param object objeto a imprimir.
	 * @return par�metros para el reporte.
	 */
	public abstract Map getParameters(Object object);
}
