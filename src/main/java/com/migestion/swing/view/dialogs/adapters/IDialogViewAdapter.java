package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;


/**
 * Inteface para un panel a utilizar en un diálogo para visualizar
 * una entidad.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface IDialogViewAdapter {

	/**
	 * crea el panel que contiene la info del objeto.
	 * @return
	 */
	Container getViewPanel();

	/**
	 * muestra el objeto a actualizar.
	 * @param object
	 */
	void showObject(Object object);

	
	
}