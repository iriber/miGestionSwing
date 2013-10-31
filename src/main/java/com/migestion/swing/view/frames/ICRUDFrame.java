package com.migestion.swing.view.frames;

import java.net.URL;

import javax.swing.JComponent;

import com.migestion.swing.view.dialogs.ICriteriaPanel;

/**
 * interface para utilizar el frame para CRUD.
 * 
 * @author bernardo
 *
 */
public interface ICRUDFrame{
	
//	/**
//	 * criteria para la búsqueda de entidades.
//	 * @return
//	 */
//	UICriteria getUICriteria() throws ViewException ;
//	
//	/**
//	 * setea el criteria para la búsqueda de entidades.
//	 */
//	void setUICriteria(UICriteria criteria);
//	
//	/**
//	 * panel para ingresar los datos de búsqueda.
//	 * @return
//	 */
//	Container getUICriteriaPanel();

	/**
	 * panel para el criterio de búsqueda.
	 * @return
	 */
	ICriteriaPanel getUICriteriaPanel();
	
	/**
	 * setea los links que utilizará la ventana.
	 * @param frame
	 */
	void setLinks( CRUDFrame frame );
	

	/**
	 * título para el menú.
	 * @return
	 */
	String getMenuTitle();

	/**
	 * ícono para la ventana.
	 * @return
	 */
	URL getIcon();

	/**
	 * footer de la ventana.
	 * @return
	 */
	JComponent getFooter(); 
	
}
