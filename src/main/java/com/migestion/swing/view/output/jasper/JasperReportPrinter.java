package com.migestion.swing.view.output.jasper;

import java.util.Map;

import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import com.migestion.swing.model.UICollection;
import com.migestion.swing.view.output.IReportPrinter;
import com.migestion.swing.view.output.exceptions.ReportPrinterException;

/**
 * Imprime UICollections con Jasper Report.
 * Deber� subclasificarse para definir el path del xml y los par�metros del reporte.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public abstract class JasperReportPrinter implements IReportPrinter{

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.view.output.IReportPrinter#print(java.lang.String, com.ostrich.commonui.model.UICollection)
	 */
	public void print(UICollection collection) throws ReportPrinterException {
		
		//creamos el data source con la colecci�n.
		JRTableModelDataSource source = new JRTableModelDataSource(collection);
		
		System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");   

		try {
			//compilamos el reporte.
			JasperReport jasperReport = JasperCompileManager.compileReport(getXMLPath());
		    //formamos el reporte.
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, getParameters(), source);
		    //visualizamos el reporte.
			JasperViewer.viewReport(jasperPrint, false);
		    
		} catch (JRException e) {
			throw new ReportPrinterException(e);
		}
	}
	
	/**
	 * 
	 * @param tableModel
	 * @throws ReportPrinterException
	 */
	public void print(TableModel tableModel) throws ReportPrinterException {
		
		//creamos el data source con la colecci�n.
		JRTableModelDataSource source = new JRTableModelDataSource(tableModel);
		
		System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");   
		
		try {
			//compilamos el reporte.
			JasperReport jasperReport = JasperCompileManager.compileReport(getXMLPath());
			//formamos el reporte.
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, getParameters(), source);
			//visualizamos el reporte.
			JasperViewer.viewReport(jasperPrint, false);
			
		} catch (JRException e) {
			throw new ReportPrinterException(e);
		}
	}

	/**
	 * retorna el path donde se encuentra el xml del reporte.
	 * @return
	 */
	public abstract String getXMLPath();
	
	/**
	 * retorna los par�metros para el reporte.
	 * @return
	 */
	public abstract Map getParameters();
}
