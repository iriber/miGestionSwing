package com.migestion.swing.resources;

import java.util.ResourceBundle;


/**
 * Para cada imagen definida en las properties creamos
 * una constante para que sea usada por toda la aplicación
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public class PropertiesImage {

	private static ResourceBundle prop = ResourceBundle.getBundle("com.migestion.swing.resources.imageUI");  

	public static String PATH_IMAGES = prop.getString("images.path");
	
	public static final String report_list_collection = PATH_IMAGES + prop.getString("report.list.collection");
	
	public static final String item_list_selection = prop.getString("item.list.selection");
	
}
