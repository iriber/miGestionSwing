package com.migestion.swing.view.output.helpers;

import com.migestion.swing.model.IModelToPrint;

/**
 * Toma elementos de un table model y
 * genera un reporte html
 * 
 * @author Bernardo Iribarbne
 *
 */
public class CollectionToHtml {

	/**
	 * formatea los elementos del model a html.
	 * 
	 * @param model
	 * @return
	 */
	public static String collectionToHtml(IModelToPrint model){
		StringBuffer outHtml = new StringBuffer();
		
		outHtml.append("<table width=670 border=1 cellpadding=10 cellspacing=0 align=\"center\">");
		
		//header del listado
		outHtml.append(getHeader(model));
		
		//elementos del listado
				
		for(int rowIndex=0; rowIndex < model.getRownCountToPrint(); rowIndex++){
			outHtml.append(getRow(model, rowIndex));	
		}
		
		outHtml.append("</table>");
		
		//footer del listado
		outHtml.append(getFooter(model));
		
		
		
		return outHtml.toString();
	}
	
	/*
	 * genera el header con los nombres de las columnas
	 * del model.
	 */
	private static String getHeader(IModelToPrint model){
		StringBuffer header = new StringBuffer();
		header.append("<tr class=\"backgroundHeader\">");
		for(int columnIndex=0; columnIndex < model.getColumnCountToPrint(); columnIndex++){
			header.append(getTh(model.getColumnNameToPrint(columnIndex)));	
		}
		header.append("</tr>");
		return header.toString();
	}

	/*
	 * genera una fila con las propiedades del objeto
	 * ubicado en dicha fila.
	 */
	private static String getRow(IModelToPrint model, int rowIndex){
		StringBuffer row = new StringBuffer();
		row.append("<tr class=\"backgroundRow\">");
		for(int columnIndex=0; columnIndex < model.getColumnCountToPrint(); columnIndex++){
			row.append(getTd(model.getValueAtToPrint(rowIndex, columnIndex)));	
		}
		row.append("</tr>");
		return row.toString();
	}

	/*
	 * th para el listado.
	 */
	private static String getTh(String value){
		StringBuffer th = new StringBuffer();
		th.append("<td class=\"header\"><center>" + formatEmpty(value) + "</center></td>");
		return th.toString();
	}
	
	/*
	 * td para el listado. 
	 */
	private static String getTd(Object object){
		StringBuffer td = new StringBuffer();
		td.append("<td class=\"row\"><center>" + formatEmpty(object) + "</center></td>");		
		return td.toString();
	}
	
	/*
	 * genera el footer de acuerdo al model.
	 */
	private static String getFooter(IModelToPrint model){
		StringBuffer footer = new StringBuffer();
		footer.append("<tr rowspan=\"4\">");
		footer.append("<td>&nbsp;</td>");
		footer.append("</tr>");
		footer.append("<tr>");
		footer.append("<td>");
		//dejamos que el tableModel establezca el footer, para
		//tener la posibilidad de customizarlo.
		footer.append(model.getFooter());
		footer.append("</td>");
		footer.append("</tr>");
		return footer.toString();
	}

	/*
	 * en caso de ser un string vac�o nos da el
	 * c�digo de escape de html.
	 */
	private static String formatEmpty(Object value){
		String s = "&nbsp;";		
		if(value!=null && value.toString().length()>0)
			s = value.toString();		
		return s;
	}		
}
